package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.dto.ComentarioRequestDTO;
import com.fullstack3.threads.dto.ComentarioResponseDTO;

public interface ComentarioService {

    List<ComentarioResponseDTO> getAllComentarios();
    ComentarioResponseDTO getComentarioById(Long id);
    List<ComentarioResponseDTO> getComentariosByTema(Long idTema);
    ComentarioResponseDTO createComentario(ComentarioRequestDTO request);
    ComentarioResponseDTO updateComentario(Long id, ComentarioRequestDTO request);
    void deleteComentario(Long id);
    
}