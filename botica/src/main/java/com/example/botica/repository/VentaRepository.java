package com.example.botica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.botica.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

  List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);

  @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
  Long totalVentasBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

  // Obtener último serie y número para la boleta de venta
  @Query("SELECT MAX(v.numero) FROM Venta v WHERE v.serie = :serie")
  String findMaxNumeroBySerie(String serie);

  // para grafico
  // Contar ventas de un día específico
  @Query("SELECT COUNT(v) FROM Venta v WHERE v.fecha = :fecha")
  Long cantidadVentasPorFecha(@Param("fecha") LocalDate fecha);

  // Opcional: contar ventas entre fechas (si quieres)
  @Query("SELECT v.fecha, COUNT(v) FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin GROUP BY v.fecha ORDER BY v.fecha")
  List<Object[]> cantidadVentasPorRango(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
