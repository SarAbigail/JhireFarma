package com.example.botica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.botica.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

  // Obtener último serie y número para la boleta de venta
  // @Query("SELECT MAX(v.numero) FROM Venta v WHERE v.serie = :serie")
  // Integer findMaxNumeroBySerie(String serie);
  @Query("SELECT MAX(v.numero) FROM Venta v WHERE v.serie = :serie")
  String findMaxNumeroBySerie(String serie);

  // Ventas por fecha exacta
  List<Venta> findByFecha(LocalDate fecha);

  // // Ventas entre fechas (se usa para semana, mes, año)
  // List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);

  // // Productos más vendidos
  // @Query("SELECT v.producto.nombre, SUM(v.cantidad) as total "
  // + "FROM Venta v GROUP BY v.producto.nombre ORDER BY total DESC")
  // List<Object[]> productosMasVendidos();

  // // Productos menos vendidos
  // @Query("SELECT v.producto.nombre, SUM(v.cantidad) as total "
  // + "FROM Venta v GROUP BY v.producto.nombre ORDER BY total ASC")
  // List<Object[]> productosMenosVendidos();

  // // Número total de ventas generadas
  // @Query("SELECT COUNT(v) FROM Venta v")
  // Long totalVentas();
}
