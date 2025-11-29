package com.example.botica.repository;

import com.example.botica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

  // Método de búsqueda por múltiples campos
  @Query("SELECT p FROM Producto p " +
      "WHERE CAST(p.id AS string) LIKE %:filtro% " +
      "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
      "OR LOWER(p.presentacion) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
      "OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
      "OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :filtro, '%'))")
  List<Producto> buscarPorVariosCampos(@Param("filtro") String filtro);

  // Obtiene las 5 marcos por categoría
  @Query("SELECT p.marca FROM Producto p WHERE p.categoria = :categoria GROUP BY p.marca ORDER BY COUNT(p) DESC LIMIT 5")
  List<String> findTop5MarcasByCategoria(String categoria);

  // Obtiene los 5 productos con más visitas
  List<Producto> findTop5ByOrderByVisitasDesc();

  // Obtiene marca de productos por categoria
  List<Producto> findByCategoriaIgnoreCase(String categoria);

  // Obtiene nombre de productos
  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  Optional<Producto> findById(Long productoId);

}