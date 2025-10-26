package com.example.botica.repository;

import com.example.botica.model.Usuarioo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariooRepository extends JpaRepository<Usuarioo, Long> {
  Optional<Usuarioo> findByUsername(String username);
}
