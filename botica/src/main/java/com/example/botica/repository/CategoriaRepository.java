package com.example.botica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
