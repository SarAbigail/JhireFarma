package com.example.botica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.Sintoma;

public interface SintomaRepository extends JpaRepository<Sintoma, Integer> {
}
