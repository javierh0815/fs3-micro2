package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.model.Categoria;
import com.fullstack3.threads.repository.TemaRepository;
import com.fullstack3.threads.repository.CategoriaRepository; 
import com.fullstack3.threads.exception.TemaNotFoundExceptionHandler;


@Service
public class TemaServiceImpl implements TemaService {

    private final TemaRepository temaRepository;
    private final CategoriaRepository categoriaRepository; 

    public TemaServiceImpl(TemaRepository temaRepository, CategoriaRepository categoriaRepository){
        this.temaRepository = temaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Tema> getAllTemas() {
        return temaRepository.findAll();
    }

    @Override
    public Tema getTemaById(Long id) {
        return temaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Tema createTema(Tema tema) {

        if (tema.getCategoria() != null && tema.getCategoria().getIdCategoria() != null) {
            Categoria categoriaReal = categoriaRepository.findById(tema.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + tema.getCategoria().getIdCategoria()));
            tema.setCategoria(categoriaReal);
        }

        tema.setFechaCreacion(LocalDateTime.now());
        
        return temaRepository.save(tema);
    }

    @Override
    public Tema updateTema(Long id, Tema tema) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));

        existingTema.setTitulo(tema.getTitulo());
        existingTema.setCodUsuario(tema.getCodUsuario());
        existingTema.setNombreUsuario(tema.getNombreUsuario());
        existingTema.setFechaModificacion(LocalDateTime.now());

        return temaRepository.save(existingTema);
    }

    @Override
    public void deleteTema(Long id) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));
        temaRepository.delete(existingTema);
    }
}