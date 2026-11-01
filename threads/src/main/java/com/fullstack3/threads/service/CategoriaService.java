package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.model.Categoria;

public interface CategoriaService {

    List<Categoria> getAllCategorias();
    Categoria getCategoriaById(Long id);
    Categoria createCategoria(Categoria categoria);
    Categoria updateCategoria(Long id, Categoria categoria);
    void deleteCategoria(Long id);
    
}
