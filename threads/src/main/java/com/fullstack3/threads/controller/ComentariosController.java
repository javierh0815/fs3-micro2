package com.fullstack3.threads.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import java.util.List;

import com.fullstack3.threads.model.Comentario;
import com.fullstack3.threads.service.ComentarioService;


@RestController
@RequestMapping("/comentarios")
public class ComentariosController {

    private final ComentarioService comentarioService;

    public ComentariosController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> getAllComentarios() {
        List<Comentario> comentarios = comentarioService.getAllComentarios();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Long id) {
        Comentario comentario = comentarioService.getComentarioById(id);
        if (comentario != null) {
            return ResponseEntity.ok(comentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@Valid @RequestBody Comentario comentario) {
        Comentario createdComentario = comentarioService.createComentario(comentario);
        return new ResponseEntity<>(createdComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
        Comentario updatedComentario = comentarioService.updateComentario(id, comentario);
        if (updatedComentario != null) {
            return ResponseEntity.ok(updatedComentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





    
}
