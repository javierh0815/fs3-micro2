package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.model.Comentario;

public interface ComentarioService {

    List<Comentario> getAllComentarios();
    Comentario getComentarioById(Long id);
    Comentario createComentario(Comentario comentario);
    Comentario updateComentario(Long id, Comentario comentario);
    void deleteComentario(Long id);
    
}
