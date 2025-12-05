package com.example.botica.repository.tienda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.botica.model.tienda.Pedido;
import com.example.botica.model.tienda.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
  List<Pedido> findByUsuario(Usuario usuario);

  List<Pedido> findByUsuarioEmail(String email);
}
