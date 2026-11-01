package com.fullstack3.threads.service;

import java.util.List;
import com.fullstack3.threads.model.Tema;


public interface TemaService {
    
    List<Tema> getAllTemas();
    Tema getTemaById(Long id);
    Tema createTema(Tema tema);
    Tema updateTema(Long id, Tema tema);
    void deleteTema(Long id);
    
}
