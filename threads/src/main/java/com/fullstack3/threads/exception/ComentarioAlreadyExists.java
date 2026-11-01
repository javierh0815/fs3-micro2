package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.CONFLICT)
public class ComentarioAlreadyExists extends RuntimeException {
    public ComentarioAlreadyExists(String codigo_comentario) {
        super("Comentario ya existe: " + codigo_comentario);
    }
}
