package com.fullstack3.threads.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import java.util.List;

import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.service.TemaService;

@RestController
@RequestMapping("/temas")
public class TemasController {

    private final TemaService temaService;

    public TemasController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public ResponseEntity<List<Tema>> getAllTemas() {
        List<Tema> temas = temaService.getAllTemas();
        return new ResponseEntity<>(temas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getTemaById(@PathVariable Long id) {
        Tema tema = temaService.getTemaById(id);
        if (tema != null) {
            return ResponseEntity.ok(tema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tema> createTema(@Valid @RequestBody Tema tema) {
        Tema createdTema = temaService.createTema(tema);
        return new ResponseEntity<>(createdTema, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> updateTema(@PathVariable Long id, @Valid @RequestBody Tema tema) {
        Tema updatedTema = temaService.updateTema(id, tema);
        if (updatedTema != null) {
            return ResponseEntity.ok(updatedTema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTema(@PathVariable Long id) {
        temaService.deleteTema(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }











}
