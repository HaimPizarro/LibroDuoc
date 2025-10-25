package com.example.libreriaduoc.libreriaduoc.controller;

import com.example.libreriaduoc.libreriaduoc.model.LibroModel;
import com.example.libreriaduoc.libreriaduoc.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase LibroController
 * Expone los endpoints REST para gestionar Libros.
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService service;

    // Inyección por constructor
    public LibroController(LibroService service) {
        this.service = service;
    }

    /** GET /api/libros - Lista todos */
    @GetMapping
    public List<LibroModel> listar() {
        return service.findAll();
    }

    /** GET /api/libros/{id} - Obtiene por id (200/404) */
    @GetMapping("/{id}")
    public ResponseEntity<LibroModel> obtenerPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** POST /api/libros - Crea (201) */
    @PostMapping
    public ResponseEntity<LibroModel> crear(@RequestBody LibroModel libro) {
        LibroModel nuevo = service.save(libro);
        return ResponseEntity.status(201).body(nuevo);
    }

    /** PUT /api/libros/{id} - Actualiza (200/404) */
    @PutMapping("/{id}")
    public ResponseEntity<LibroModel> actualizar(@PathVariable Long id, @RequestBody LibroModel libro) {
        return service.findById(id)
                .map(existente -> {
                    libro.setId(id); // mantener mismo id
                    return ResponseEntity.ok(service.save(libro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** DELETE /api/libros/{id} - Elimina (204/404) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}