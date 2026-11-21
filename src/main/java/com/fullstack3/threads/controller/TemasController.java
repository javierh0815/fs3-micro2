package com.fullstack3.threads.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

import com.fullstack3.threads.dto.TemaRequestDTO;
import com.fullstack3.threads.dto.TemaResponseDTO;
import com.fullstack3.threads.service.TemaService;

@RestController
@RequestMapping("/temas")
public class TemasController {

    private final TemaService temaService;

    public TemasController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public ResponseEntity<List<TemaResponseDTO>> getAllTemas() {
        return ResponseEntity.ok(temaService.getAllTemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> getTemaById(@PathVariable Long id) {
        return ResponseEntity.ok(temaService.getTemaById(id));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<TemaResponseDTO>> getTemasByCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(temaService.getTemasByCategoria(idCategoria));
    }

    @PostMapping
    public ResponseEntity<TemaResponseDTO> createTema(@Valid @RequestBody TemaRequestDTO request) {
        TemaResponseDTO createdTema = temaService.createTema(request);
        return new ResponseEntity<>(createdTema, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> updateTema(@PathVariable Long id, @Valid @RequestBody TemaRequestDTO request) {
        return ResponseEntity.ok(temaService.updateTema(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTema(@PathVariable Long id) {
        temaService.deleteTema(id);
        return ResponseEntity.noContent().build();
    }
}