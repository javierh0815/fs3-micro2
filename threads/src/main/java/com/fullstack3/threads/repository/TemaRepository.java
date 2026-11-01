package com.fullstack3.threads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack3.threads.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    boolean existsByCodigoTema(String codigoTema);
    Optional<Tema> findByCodigoTema(String codigoTema);
    
}
