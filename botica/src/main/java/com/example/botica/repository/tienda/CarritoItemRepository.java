package com.example.botica.repository.tienda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
  Optional<CarritoItem> findByCarrito_IdAndProductoId(Integer carritoId, Integer productoId);
}