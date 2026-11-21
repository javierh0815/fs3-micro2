package com.fullstack3.threads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class CategoriaNotFoundExceptionHandler extends RuntimeException {
    public CategoriaNotFoundExceptionHandler(String codigo_categoria) {
        super("Categoria no encontrada: " + codigo_categoria);
    }
    
    public CategoriaNotFoundExceptionHandler(Long id) {
        super("Categoria no encontrada con el ID: " + id);
    }
}