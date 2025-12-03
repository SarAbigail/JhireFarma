package com.example.botica.service.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.botica.model.tienda.DetallePedido;
import com.example.botica.repository.tienda.DetallePedidoRepository;

@Service
public class DetallePedidoService {

  @Autowired
  private DetallePedidoRepository detallePedidoRepository;

  // Guardar detalle de pedidos
  public void guardar(DetallePedido detalle) {
    detallePedidoRepository.save(detalle);
  }
}
