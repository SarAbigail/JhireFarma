package com.example.botica.repository.tienda;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
