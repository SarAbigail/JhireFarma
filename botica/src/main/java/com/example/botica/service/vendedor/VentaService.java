package com.example.botica.service.vendedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.botica.model.Producto;
import com.example.botica.model.Venta;
import com.example.botica.model.VentaDetalle;
import com.example.botica.repository.ProductoRepository;
import com.example.botica.repository.VentaRepository;

@Service
public class VentaService {

  @Autowired
  private VentaRepository ventaRepository;

  @Autowired
  private ProductoRepository productoRepository;

  public Venta prepararNuevaVenta() {

    Venta v = new Venta();
    String serie = "B001";

    // Obtener último número como string
    String ultimo = ventaRepository.findMaxNumeroBySerie(serie);

    // Si no hay registros, empezar en 1
    int siguiente = (ultimo == null)
        ? 1
        : Integer.parseInt(ultimo) + 1;

    v.setSerie(serie);
    v.setNumero(String.format("%06d", siguiente)); // ahora sí
    System.out.println("NUMERO GENERADO = " + v.getNumero());

    return v;
  }

  // public Venta guardarVenta(Venta venta) {
  // return ventaRepository.save(venta);
  // }

  public Venta registrarVenta(Venta ventaRecibida) {

    Venta venta = new Venta();
    venta.setSerie(ventaRecibida.getSerie());
    venta.setNumero(ventaRecibida.getNumero());
    venta.setFecha(LocalDate.now());
    venta.setCliente(ventaRecibida.getCliente());
    venta.setMetodoPago(ventaRecibida.getMetodoPago());
    venta.setTotal(ventaRecibida.getTotal());

    List<VentaDetalle> detallesGuardados = new ArrayList<>();

    for (VentaDetalle d : ventaRecibida.getDetalles()) {

      Producto producto = productoRepository.findById(d.getProducto().getId()).orElse(null);
      if (producto == null)
        continue;

      VentaDetalle detalle = new VentaDetalle();
      detalle.setVenta(venta);
      detalle.setProducto(producto);
      detalle.setCantidad(d.getCantidad());
      detalle.setPrecio(d.getPrecio());
      detalle.setSubtotal(d.getSubtotal());

      detallesGuardados.add(detalle);
    }

    venta.setDetalles(detallesGuardados);

    return ventaRepository.save(venta);
  }
}
