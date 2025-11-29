package com.example.botica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
