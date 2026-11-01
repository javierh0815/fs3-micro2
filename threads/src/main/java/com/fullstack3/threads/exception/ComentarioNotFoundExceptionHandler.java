package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class ComentarioNotFoundExceptionHandler extends RuntimeException {
    public ComentarioNotFoundExceptionHandler(String codigo_comentario) {
        super("Comentario no encontrado: " + codigo_comentario);
    }
    
    public ComentarioNotFoundExceptionHandler(Long id) {
        super("Comentario no encontrado con el ID: " + id);
    }
    
}
