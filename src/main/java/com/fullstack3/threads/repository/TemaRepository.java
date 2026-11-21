package com.fullstack3.threads.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack3.threads.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    boolean existsByCodigoTema(Integer codigoTema);
    Optional<Tema> findByCodigoTema(Integer codigoTema);
    List<Tema> findByCategoriaIdCategoria(Long idCategoria);
    List<Tema> findByCodUsuario(Integer codUsuario);
    List<Tema> findByFechaCierreIsNull();
}