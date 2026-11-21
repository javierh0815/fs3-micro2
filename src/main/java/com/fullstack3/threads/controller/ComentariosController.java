package com.fullstack3.threads.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

import com.fullstack3.threads.dto.ComentarioRequestDTO;
import com.fullstack3.threads.dto.ComentarioResponseDTO;
import com.fullstack3.threads.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {

    private final ComentarioService comentarioService;

    public ComentariosController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponseDTO>> getAllComentarios() {
        return ResponseEntity.ok(comentarioService.getAllComentarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> getComentarioById(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.getComentarioById(id));
    }

    @GetMapping("/tema/{idTema}")
    public ResponseEntity<List<ComentarioResponseDTO>> getComentariosByTema(@PathVariable Long idTema) {
        return ResponseEntity.ok(comentarioService.getComentariosByTema(idTema));
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> createComentario(@Valid @RequestBody ComentarioRequestDTO request) {
        ComentarioResponseDTO createdComentario = comentarioService.createComentario(request);
        return new ResponseEntity<>(createdComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> updateComentario(@PathVariable Long id, @Valid @RequestBody ComentarioRequestDTO request) {
        return ResponseEntity.ok(comentarioService.updateComentario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}