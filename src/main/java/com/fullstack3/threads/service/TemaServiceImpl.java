package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fullstack3.threads.client.UsuarioClient;
import com.fullstack3.threads.dto.TemaRequestDTO;
import com.fullstack3.threads.dto.TemaResponseDTO;
import com.fullstack3.threads.dto.UsuarioExternoDTO;
import com.fullstack3.threads.exception.CategoriaNotFoundExceptionHandler;
import com.fullstack3.threads.exception.TemaNotFoundExceptionHandler;
import com.fullstack3.threads.model.Categoria;
import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.repository.CategoriaRepository;
import com.fullstack3.threads.repository.TemaRepository;

@Service
public class TemaServiceImpl implements TemaService {

    private final TemaRepository temaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioClient usuarioClient;

    public TemaServiceImpl(TemaRepository temaRepository,
                           CategoriaRepository categoriaRepository,
                           UsuarioClient usuarioClient) {
        this.temaRepository = temaRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioClient = usuarioClient;
    }

    @Override
    public List<TemaResponseDTO> getAllTemas() {
        return temaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TemaResponseDTO getTemaById(Long id) {
        Tema tema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));
        return mapToResponseDTO(tema);
    }

    @Override
    public List<TemaResponseDTO> getTemasByCategoria(Long idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + idCategoria);
        }
        return temaRepository.findByCategoriaIdCategoria(idCategoria)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TemaResponseDTO createTema(TemaRequestDTO request) {
        // 1. Validar que la categoría exista y no esté cerrada
        Categoria categoriaReal = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + request.getIdCategoria()));

        if (categoriaReal.getFechaCierre() != null) {
            throw new IllegalStateException("No se pueden crear temas en una categoría cerrada.");
        }

        // 2. Consultar usuario en el microservicio usercontrol
        UsuarioExternoDTO usuarioExterno = usuarioClient.obtenerUsuarioPorCodigo(request.getCodUsuario());
        if (usuarioExterno == null || !Boolean.TRUE.equals(usuarioExterno.getFlagActivo())) {
            throw new IllegalStateException("El usuario no existe o se encuentra inactivo.");
        }

        // 3. Construir el tema con el snapshot del nombreUsuario real
        Tema nuevoTema = new Tema();
        nuevoTema.setTitulo(request.getTitulo());
        nuevoTema.setCodUsuario(usuarioExterno.getCodUsuario());
        nuevoTema.setNombreUsuario(usuarioExterno.getNombreUsuario());
        nuevoTema.setCategoria(categoriaReal);
        nuevoTema.setFechaCreacion(LocalDateTime.now());
        nuevoTema.setFechaModificacion(LocalDateTime.now());

        Tema guardado = temaRepository.save(nuevoTema);
        return mapToResponseDTO(guardado);
    }

    @Override
    public TemaResponseDTO updateTema(Long id, TemaRequestDTO request) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));

        // Validar si cambió de categoría
        if (!existingTema.getCategoria().getIdCategoria().equals(request.getIdCategoria())) {
            Categoria nuevaCategoria = categoriaRepository.findById(request.getIdCategoria())
                    .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + request.getIdCategoria()));
            existingTema.setCategoria(nuevaCategoria);
        }

        // Validar usuario en usercontrol y refrescar snapshot
        UsuarioExternoDTO usuarioExterno = usuarioClient.obtenerUsuarioPorCodigo(request.getCodUsuario());
        if (usuarioExterno == null || !Boolean.TRUE.equals(usuarioExterno.getFlagActivo())) {
            throw new IllegalStateException("El usuario no existe o se encuentra inactivo.");
        }

        existingTema.setTitulo(request.getTitulo());
        existingTema.setCodUsuario(usuarioExterno.getCodUsuario());
        existingTema.setNombreUsuario(usuarioExterno.getNombreUsuario());
        existingTema.setFechaModificacion(LocalDateTime.now());

        Tema actualizado = temaRepository.save(existingTema);
        return mapToResponseDTO(actualizado);
    }

    @Override
    public void deleteTema(Long id) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));
        temaRepository.delete(existingTema);
    }

    private TemaResponseDTO mapToResponseDTO(Tema tema) {
        return new TemaResponseDTO(
                tema.getIdTema(),
                tema.getCodigoTema(),
                tema.getTitulo(),
                tema.getCodUsuario(),
                tema.getNombreUsuario(),
                tema.getCategoria() != null ? tema.getCategoria().getIdCategoria() : null,
                tema.getCategoria() != null ? tema.getCategoria().getNombreCategoria() : null,
                tema.getFechaCreacion(),
                tema.getFechaModificacion(),
                tema.getFechaCierre()
        );
    }
}