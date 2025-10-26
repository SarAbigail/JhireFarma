package com.example.botica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // ADMIN, VENDEDOR, CLIENTE

    // Getters y Setters
}
