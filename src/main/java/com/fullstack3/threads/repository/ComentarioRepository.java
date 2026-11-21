package com.fullstack3.threads.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack3.threads.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    boolean existsByCodigoComentario(Integer codigoComentario);
    Optional<Comentario> findByCodigoComentario(Integer codigoComentario);
    List<Comentario> findByTemaIdTema(Long idTema);
    List<Comentario> findByCodUsuario(Integer codUsuario);
}