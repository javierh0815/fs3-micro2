package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus (HttpStatus.CONFLICT)
public class TemaAlreadyExistsExceptionHandler extends RuntimeException {
    public TemaAlreadyExistsExceptionHandler(String codigo_tema) {
        super("Tema ya existe: " + codigo_tema);
    }
    
}
