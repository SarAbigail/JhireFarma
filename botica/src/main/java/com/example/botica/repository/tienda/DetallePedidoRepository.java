package com.example.botica.repository.tienda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
  List<DetallePedido> findByPedidoId(Integer pedidoId);
}
