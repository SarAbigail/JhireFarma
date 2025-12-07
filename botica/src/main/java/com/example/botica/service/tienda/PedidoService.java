package com.example.botica.service.tienda;

import java.time.LocalDate;
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

  // Obtener pedido por Id
  public Pedido obtenerPorId(Integer id) {
    return pedidoRepository.findById(id).orElse(null);
  }

  // Para filtrar reservas por estado y fecha
  public List<Pedido> listarConFiltrosEstadoYRangoFecha(String estado, LocalDate fechaInicio, LocalDate fechaFin) {
    return pedidoRepository.buscarConFiltrosEstadoYRangoFecha(estado, fechaInicio, fechaFin);
  }

  // Cambiar el estado del pedido
  public void cambiarEstadoReservaPedido(Integer id, String nuevoEstado) {
    Pedido p = obtenerPorId(id);
    if (p != null) {
      p.setEstado(nuevoEstado);
      pedidoRepository.save(p);
    }
  }
}
