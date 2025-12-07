package com.example.botica.repository.tienda;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.botica.model.tienda.Pedido;
import com.example.botica.model.tienda.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

  List<Pedido> findByUsuario(Usuario usuario);

  List<Pedido> findByUsuarioEmail(String email);

  // Para filtrar reservas por estado y fecha
  @Query("""
          SELECT p FROM Pedido p
          WHERE (:estado = '' OR :estado IS NULL OR p.estado = :estado)
          AND (:fechaInicio IS NULL OR p.fecha >= :fechaInicio)
          AND (:fechaFin IS NULL OR p.fecha <= :fechaFin)
      """)

  List<Pedido> buscarConFiltrosEstadoYRangoFecha(
      @Param("estado") String estado,
      @Param("fechaInicio") LocalDate fechaInicio,
      @Param("fechaFin") LocalDate fechaFin);

}
