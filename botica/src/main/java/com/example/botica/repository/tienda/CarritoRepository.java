package com.example.botica.repository.tienda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
  Optional<Carrito> findByUsuario_Id(Integer usuarioId);
}