package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.dto.TemaRequestDTO;
import com.fullstack3.threads.dto.TemaResponseDTO;

public interface TemaService {

    List<TemaResponseDTO> getAllTemas();
    TemaResponseDTO getTemaById(Long id);
    List<TemaResponseDTO> getTemasByCategoria(Long idCategoria);
    TemaResponseDTO createTema(TemaRequestDTO request);
    TemaResponseDTO updateTema(Long id, TemaRequestDTO request);
    void deleteTema(Long id);
    
}