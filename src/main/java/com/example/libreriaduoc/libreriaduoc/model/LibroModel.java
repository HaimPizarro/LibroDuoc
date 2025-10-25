package com.example.libreriaduoc.libreriaduoc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "libro", schema = "public")
public class LibroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ok si la columna id es identity/bigserial
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(name = "anio_publicacion")
    private LocalDate anioPublicacion;

    @Column(length = 100)
    private String genero;
}