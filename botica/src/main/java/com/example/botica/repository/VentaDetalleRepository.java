package com.example.botica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.botica.model.VentaDetalle;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Integer> {

  @Query("SELECT d.producto.nombre, SUM(d.cantidad) FROM VentaDetalle d " +
      "WHERE d.venta.fecha BETWEEN :inicio AND :fin " +
      "GROUP BY d.producto.nombre ORDER BY SUM(d.cantidad) DESC")
  List<Object[]> productosMasVendidos(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

  @Query("SELECT d.producto.nombre, SUM(d.cantidad) FROM VentaDetalle d " +
      "WHERE d.venta.fecha BETWEEN :inicio AND :fin " +
      "GROUP BY d.producto.nombre ORDER BY SUM(d.cantidad) ASC")
  List<Object[]> productosMenosVendidos(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
