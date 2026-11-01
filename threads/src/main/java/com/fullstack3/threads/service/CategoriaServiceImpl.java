package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import com.fullstack3.threads.model.Categoria;
import com.fullstack3.threads.repository.CategoriaRepository;
import com.fullstack3.threads.exception.CategoriaNotFoundExceptionHandler;
import com.fullstack3.threads.exception.CategoriaAlreadyExists;

@Service
public class CategoriaServiceImpl implements CategoriaService {


    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override 
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElse(null);
    }
    
    @Override
    public Categoria createCategoria(Categoria categoria) {
        if (categoriaRepository.existsByCodigoCategoria(categoria.getCodigo_categoria())) {
            throw new CategoriaAlreadyExists("La categoría con el código " + categoria.getCodigo_categoria() + " ya existe.");
        }
        categoria.setFechaCreacion(LocalDateTime.now());
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + id));
        
        existingCategoria.setNombre_categoria(categoria.getNombre_categoria());
        existingCategoria.setCodigo_categoria(categoria.getCodigo_categoria());
        existingCategoria.setFechaModificacion(LocalDateTime.now());
        
        return categoriaRepository.save(existingCategoria);
    }


    @Override
    public void deleteCategoria(Long id) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + id));
        categoriaRepository.delete(existingCategoria);
    }
    
}