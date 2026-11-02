package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import com.fullstack3.threads.model.Comentario;
import com.fullstack3.threads.repository.ComentarioRepository;
import com.fullstack3.threads.exception.ComentarioNotFoundExceptionHandler;
import com.fullstack3.threads.exception.ComentarioAlreadyExists;

@Service 
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario getComentarioById(Long id) {
        return comentarioRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Comentario createComentario(Comentario comentario) {
        if (comentarioRepository.existsByCodigoComentario(comentario.getCodigo_comentario())) {
            throw new ComentarioAlreadyExists("El comentario con el código " + comentario.getCodigo_comentario() + " ya existe.");
        }
        comentario.setFechaCreacion(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario updateComentario(Long id, Comentario comentario) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundExceptionHandler("Comentario no encontrado con id: " + id));

        existingComentario.setContenido(comentario.getContenido());
        existingComentario.setCodUsuario(comentario.getCodUsuario());
        existingComentario.setNombreUsuario(comentario.getNombreUsuario());
        existingComentario.setCodigo_comentario(comentario.getCodigo_comentario());
        existingComentario.setFechaModificacion(LocalDateTime.now());

        return comentarioRepository.save(existingComentario);
    }

    @Override
    public void deleteComentario(Long id) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNotFoundExceptionHandler("Comentario no encontrado con id: " + id));
        comentarioRepository.delete(existingComentario);
    }
    
}
