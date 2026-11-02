package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import com.fullstack3.threads.model.Comentario;
import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.repository.ComentarioRepository;
import com.fullstack3.threads.repository.TemaRepository;
import com.fullstack3.threads.exception.ComentarioNotFoundExceptionHandler;

@Service 
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final TemaRepository temaRepository; 

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, TemaRepository temaRepository){
        this.comentarioRepository = comentarioRepository;
        this.temaRepository = temaRepository;
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

        if (comentario.getTema() != null && comentario.getTema().getIdTema() != null) {
            Tema temaReal = temaRepository.findById(comentario.getTema().getIdTema())
                    .orElseThrow(() -> new RuntimeException("Tema no encontrado con id: " + comentario.getTema().getIdTema()));
            comentario.setTema(temaReal);
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