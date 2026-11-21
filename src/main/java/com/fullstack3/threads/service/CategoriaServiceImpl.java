package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fullstack3.threads.dto.CategoriaRequestDTO;
import com.fullstack3.threads.dto.CategoriaResponseDTO;
import com.fullstack3.threads.exception.CategoriaAlreadyExists;
import com.fullstack3.threads.exception.CategoriaNotFoundExceptionHandler;
import com.fullstack3.threads.model.Categoria;
import com.fullstack3.threads.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaResponseDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + id));
        return mapToResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO request) {
        if (categoriaRepository.existsByNombreCategoriaIgnoreCase(request.getNombreCategoria())) {
            throw new CategoriaAlreadyExists("La categoría '" + request.getNombreCategoria() + "' ya existe.");
        }

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombreCategoria(request.getNombreCategoria());
        nuevaCategoria.setFechaCreacion(LocalDateTime.now());
        nuevaCategoria.setFechaModificacion(LocalDateTime.now());

        Categoria guardada = categoriaRepository.save(nuevaCategoria);
        return mapToResponseDTO(guardada);
    }

    @Override
    public CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO request) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + id));

        existingCategoria.setNombreCategoria(request.getNombreCategoria());
        existingCategoria.setFechaModificacion(LocalDateTime.now());

        Categoria actualizada = categoriaRepository.save(existingCategoria);
        return mapToResponseDTO(actualizada);
    }

    @Override
    public void deleteCategoria(Long id) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExceptionHandler("Categoría no encontrada con id: " + id));
        categoriaRepository.delete(existingCategoria);
    }

    private CategoriaResponseDTO mapToResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getCodigoCategoria(),
                categoria.getNombreCategoria(),
                categoria.getFechaCreacion(),
                categoria.getFechaModificacion(),
                categoria.getFechaCierre()
        );
    }
}