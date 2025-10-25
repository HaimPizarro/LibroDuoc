package com.example.libreriaduoc.libreriaduoc.repository;

import com.example.libreriaduoc.libreriaduoc.model.LibroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<LibroModel, Long> {
}