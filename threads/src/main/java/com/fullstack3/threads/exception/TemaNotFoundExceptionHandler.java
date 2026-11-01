package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus (HttpStatus.NOT_FOUND)
public class TemaNotFoundExceptionHandler extends RuntimeException {

    public TemaNotFoundExceptionHandler(String codigo_tema) {
        super("Tema no encontrado: " + codigo_tema);
    }

    public TemaNotFoundExceptionHandler(Long id) {
        super("Tema no encontrado con el ID: " + id);
    }
}
