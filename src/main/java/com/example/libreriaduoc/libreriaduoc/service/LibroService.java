package com.example.libreriaduoc.libreriaduoc.service;

import com.example.libreriaduoc.libreriaduoc.model.LibroModel;
import com.example.libreriaduoc.libreriaduoc.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {

    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public List<LibroModel> findAll() {
        return repository.findAll();
    }

    public Optional<LibroModel> findById(Long id) {
        return repository.findById(id);
    }

    public LibroModel save(LibroModel libro) {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        return repository.save(libro);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}