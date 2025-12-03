package com.example.botica.service.tienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.botica.model.tienda.Pedido;
import com.example.botica.repository.tienda.PedidoRepository;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  // Guardar pedidos
  public Pedido guardar(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  // Listar pedidos
  public List<Pedido> listar() {
    return pedidoRepository.findAll();
  }

  public Pedido obtenerPorId(Integer id) {
    return pedidoRepository.findById(id).orElse(null);
  }
}
