package com.fullstack3.threads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack3.threads.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    boolean existsByCodigoComentario(String codigoComentario);
    Optional<Comentario> findByCodigoComentario(String codigoComentario);
    
}
