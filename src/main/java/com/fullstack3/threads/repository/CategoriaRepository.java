package com.fullstack3.threads.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack3.threads.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByCodigoCategoria(Integer codigoCategoria);
    Optional<Categoria> findByCodigoCategoria(Integer codigoCategoria);
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
    List<Categoria> findByFechaCierreIsNull();
}