package com.example.libreriaduoc.libreriaduoc.service;

import com.example.libreriaduoc.libreriaduoc.model.LibroModel;
import com.example.libreriaduoc.libreriaduoc.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase LibroService
 * ------------------
 * Capa intermedia entre el controlador (API REST) y el repositorio (BD).
 * Aquí puedes aplicar validaciones y reglas de negocio.
 */
@Service
@Transactional
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    /** Lista todos los libros. */
    public List<LibroModel> findAll() {
        return repository.findAll();
    }

    /** Busca por id (puede no existir). */
    public Optional<LibroModel> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda o actualiza un libro.
     * - Si id == null → INSERT
     * - Si id != null → UPDATE
     * Incluye validaciones mínimas.
     */
    public LibroModel save(LibroModel libro) {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        return repository.save(libro);
    }

    /** Elimina por id. */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}