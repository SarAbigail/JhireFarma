package com.example.botica.service.admin;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.botica.model.Producto;
import com.example.botica.model.Venta;
import com.example.botica.repository.ProductoRepository;
import com.example.botica.repository.VentaDetalleRepository;
import com.example.botica.repository.VentaRepository;

@Service
public class ReporteService {

  @Autowired
  private VentaRepository ventaRepository;

  @Autowired
  private VentaDetalleRepository detalleRepository;

  @Autowired
  private ProductoRepository productoRepository;

  public Map<String, Double> ventasEntreFechas(LocalDate inicio, LocalDate fin) {
    List<Venta> ventas = ventaRepository.findByFechaBetween(inicio, fin);
    Map<String, Double> map = new LinkedHashMap<>();
    LocalDate fecha = inicio;
    while (!fecha.isAfter(fin)) {
      map.put(fecha.toString(), 0.0);
      fecha = fecha.plusDays(1);
    }
    for (Venta v : ventas) {
      String key = v.getFecha().toString();
      map.put(key, map.get(key) + v.getTotal());
    }
    return map;
  }

  public Long totalVentasEntreFechas(LocalDate inicio, LocalDate fin) {
    Long total = ventaRepository.totalVentasBetween(inicio, fin);
    return total != null ? total : 0L;
  }

  // Grafico más vendidos + menos vendidos
  public List<Object[]> productosMasVendidosEntreFechas(LocalDate inicio, LocalDate fin) {
    return detalleRepository.productosMasVendidos(inicio, fin)
        .stream()
        .limit(5)
        .collect(Collectors.toList());
  }

  // Grafico más vendidos + menos vendidos
  public List<Object[]> productosMenosVendidosEntreFechas(LocalDate inicio, LocalDate fin) {
    return detalleRepository.productosMenosVendidos(inicio, fin)
        .stream()
        .limit(5)
        .collect(Collectors.toList());
  }

  // Total monetario de ventas
  public Long ingresosTotales(LocalDate inicio, LocalDate fin) {
    Long total = ventaRepository.totalVentasBetween(inicio, fin);
    return total != null ? total : 0L;
  }

  // Cantidad de ventas
  public Integer cantidadVentas(LocalDate inicio, LocalDate fin) {
    return ventaRepository.findByFechaBetween(inicio, fin).size();
  }

  // Inventario bajo
  // Contar inventario bajo
  public Integer inventarioBajo(int umbral) {
    return productoRepository.contarInventarioBajo(umbral);
  }

  // Detalle inventario bajo
  public List<Producto> inventarioBajoDetalle(Integer umbral) {
    return productoRepository.inventarioBajo(umbral);
  }

  public Map<String, Long> cantidadVentasPorFechaEntreFechas(LocalDate inicio, LocalDate fin) {
    Map<String, Long> result = new LinkedHashMap<>();
    LocalDate fecha = inicio;
    while (!fecha.isAfter(fin)) {
      Long cant = ventaRepository.cantidadVentasPorFecha(fecha);
      result.put(fecha.toString(), cant != null ? cant : 0L);
      fecha = fecha.plusDays(1);
    }
    return result;
  }
}
