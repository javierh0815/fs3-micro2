package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fullstack3.threads.client.UsuarioClient;
import com.fullstack3.threads.dto.ComentarioRequestDTO;
import com.fullstack3.threads.dto.ComentarioResponseDTO;
import com.fullstack3.threads.dto.UsuarioExternoDTO;
import com.fullstack3.threads.exception.ComentarioNotFoundExceptionHandler;
import com.fullstack3.threads.exception.TemaNotFoundExceptionHandler;
import com.fullstack3.threads.model.Comentario;
import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.repository.ComentarioRepository;
import com.fullstack3.threads.repository.TemaRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final TemaRepository temaRepository;
    private final UsuarioClient usuarioClient;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository,
                                 TemaRepository temaRepository,
                                 UsuarioClient usuarioClient) {
        this.comentarioRepository = comentarioRepository;
        this.temaRepository = temaRepository;
        this.usuarioClient = usuarioClient;
    }

    @Override
    public List<ComentarioResponseDTO> getAllComentarios() {
        return comentarioRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioResponseDTO getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundExceptionHandler("Comentario no encontrado con id: " + id));
        return mapToResponseDTO(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> getComentariosByTema(Long idTema) {
        if (!temaRepository.existsById(idTema)) {
            throw new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + idTema);
        }
        return comentarioRepository.findByTemaIdTema(idTema)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioResponseDTO createComentario(ComentarioRequestDTO request) {
        // 1. Validar que el tema exista y no esté cerrado
        Tema temaReal = temaRepository.findById(request.getIdTema())
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + request.getIdTema()));

        if (temaReal.getFechaCierre() != null) {
            throw new IllegalStateException("No se pueden agregar comentarios en un tema cerrado.");
        }

        // 2. Consultar usuario en el microservicio usercontrol
        UsuarioExternoDTO usuarioExterno = usuarioClient.obtenerUsuarioPorCodigo(request.getCodUsuario());
        if (usuarioExterno == null || !Boolean.TRUE.equals(usuarioExterno.getFlagActivo())) {
            throw new IllegalStateException("El usuario no existe o se encuentra inactivo.");
        }

        // 3. Construir el comentario con el snapshot del nombreUsuario real
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContenido(request.getContenido());
        nuevoComentario.setCodUsuario(usuarioExterno.getCodUsuario());
        nuevoComentario.setNombreUsuario(usuarioExterno.getNombreUsuario());
        nuevoComentario.setTema(temaReal);
        nuevoComentario.setFechaCreacion(LocalDateTime.now());
        nuevoComentario.setFechaModificacion(LocalDateTime.now());

        Comentario guardado = comentarioRepository.save(nuevoComentario);
        return mapToResponseDTO(guardado);
    }

    @Override
    public ComentarioResponseDTO updateComentario(Long id, ComentarioRequestDTO request) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundExceptionHandler("Comentario no encontrado con id: " + id));

        // Validar si cambió de tema
        if (!existingComentario.getTema().getIdTema().equals(request.getIdTema())) {
            Tema nuevoTema = temaRepository.findById(request.getIdTema())
                    .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + request.getIdTema()));
            existingComentario.setTema(nuevoTema);
        }

        // Validar usuario en usercontrol y refrescar snapshot
        UsuarioExternoDTO usuarioExterno = usuarioClient.obtenerUsuarioPorCodigo(request.getCodUsuario());
        if (usuarioExterno == null || !Boolean.TRUE.equals(usuarioExterno.getFlagActivo())) {
            throw new IllegalStateException("El usuario no existe o se encuentra inactivo.");
        }

        existingComentario.setContenido(request.getContenido());
        existingComentario.setCodUsuario(usuarioExterno.getCodUsuario());
        existingComentario.setNombreUsuario(usuarioExterno.getNombreUsuario());
        existingComentario.setFechaModificacion(LocalDateTime.now());

        Comentario actualizado = comentarioRepository.save(existingComentario);
        return mapToResponseDTO(actualizado);
    }

    @Override
    public void deleteComentario(Long id) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundExceptionHandler("Comentario no encontrado con id: " + id));
        comentarioRepository.delete(existingComentario);
    }

    private ComentarioResponseDTO mapToResponseDTO(Comentario comentario) {
        return new ComentarioResponseDTO(
                comentario.getIdComentario(),
                comentario.getCodigoComentario(),
                comentario.getContenido(),
                comentario.getCodUsuario(),
                comentario.getNombreUsuario(),
                comentario.getTema() != null ? comentario.getTema().getIdTema() : null,
                comentario.getTema() != null ? comentario.getTema().getTitulo() : null,
                comentario.getFechaCreacion(),
                comentario.getFechaModificacion(),
                comentario.getFechaCierre()
        );
    }
}