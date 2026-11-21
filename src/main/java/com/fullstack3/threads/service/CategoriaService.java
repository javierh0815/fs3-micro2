package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.dto.CategoriaRequestDTO;
import com.fullstack3.threads.dto.CategoriaResponseDTO;

public interface CategoriaService {

    List<CategoriaResponseDTO> getAllCategorias();
    CategoriaResponseDTO getCategoriaById(Long id);
    CategoriaResponseDTO createCategoria(CategoriaRequestDTO request);
    CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO request);
    void deleteCategoria(Long id);
    
}