package com.example.botica.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.botica.model.Venta;
import com.example.botica.repository.VentaRepository;

@Service
public class ReporteService {

  @Autowired
  private VentaRepository ventaRepository;

  public List<Venta> ventasDiarias() {
    return ventaRepository.findByFecha(LocalDate.now());
  }

  // public List<Venta> ventasSemanales() {
  // LocalDate hoy = LocalDate.now();
  // LocalDate inicio = hoy.minusDays(7);
  // return ventaRepository.findByFechaBetween(inicio, hoy);
  // }

  // public List<Venta> ventasMensuales() {
  // LocalDate hoy = LocalDate.now();
  // LocalDate inicio = hoy.withDayOfMonth(1);
  // return ventaRepository.findByFechaBetween(inicio, hoy);
  // }

  // public List<Venta> ventasAnuales() {
  // LocalDate hoy = LocalDate.now();
  // LocalDate inicio = hoy.withDayOfYear(1);
  // return ventaRepository.findByFechaBetween(inicio, hoy);
  // }

  // public List<Object[]> productosMasVendidos() {
  // return ventaRepository.productosMasVendidos();
  // }

  // public List<Object[]> productosMenosVendidos() {
  // return ventaRepository.productosMenosVendidos();
  // }

  // public Long totalVentas() {
  // return ventaRepository.totalVentas();
  // }
}
