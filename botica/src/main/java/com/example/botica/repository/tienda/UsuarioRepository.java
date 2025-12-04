package com.example.botica.repository.tienda;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(String email);
}
