package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.CONFLICT)
public class CategoriaAlreadyExists extends RuntimeException {
    public CategoriaAlreadyExists(String codigo_categoria) {
        super("Categoria ya existe: " + codigo_categoria);
    }
}
