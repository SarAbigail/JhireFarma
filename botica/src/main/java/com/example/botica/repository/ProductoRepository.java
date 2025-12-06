package com.example.botica.repository;

import com.example.botica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// public interface ProductoRepository extends JpaRepository<Producto, Integer>
// {

// // Método de búsqueda por múltiples campos
// @Query("SELECT p FROM Producto p " +
// "WHERE CAST(p.id AS string) LIKE %:filtro% " +
// "OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
// "OR LOWER(p.presentacion) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
// "OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
// "OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :filtro, '%'))")
// List<Producto> buscarPorVariosCampos(@Param("filtro") String filtro);

// // Obtiene las 5 marcos por categoría
// @Query("SELECT p.marca FROM Producto p WHERE p.categoria = :categoria GROUP
// BY p.marca ORDER BY COUNT(p) DESC LIMIT 5")
// List<String> findTop5MarcasByCategoria(String categoria);

// // Obtiene los 5 productos con más visitas
// List<Producto> findTop5ByOrderByVisitasDesc();

// // Obtiene nombre de productos
// List<Producto> findByNombreContainingIgnoreCase(String nombre);

// Optional<Producto> findById(Integer productoId);

// // Productos con inventario bajo
// //// Contador
// @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock <= :umbral")
// Integer contarInventarioBajo(@Param("umbral") Integer umbral);

// //// Detalle
// @Query("SELECT p FROM Producto p WHERE p.stock <= :umbral ORDER BY p.stock
// ASC")
// List<Producto> inventarioBajo(@Param("umbral") Integer umbral);

// // Obtiene productos por categoria
// List<Producto> findByCategoriaIgnoreCase(String categoria);

// // Obtiene presentaciones de productos por categoria
// List<Producto> findByPresentacionIgnoreCase(String categoria);

// }

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

  /*
   * ===========================================================
   * Búsquedas de productos
   * ===========================================================
   */

  // Buscar productos por varios campos (id, nombre, presentacion, categoria,
  // marca)
  @Query("""
      SELECT p FROM Producto p
      WHERE CAST(p.id AS string) LIKE %:filtro%
         OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
         OR LOWER(p.presentacion) LIKE LOWER(CONCAT('%', :filtro, '%'))
         OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :filtro, '%'))
         OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :filtro, '%'))
      """)
  List<Producto> buscarPorVariosCampos(@Param("filtro") String filtro);

  // Buscar por nombre
  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  // Buscar por ID
  Optional<Producto> findById(Integer productoId);

  /*
   * ===========================================================
   * Los destacados
   * ===========================================================
   */

  // Top 5 productos más visitados
  List<Producto> findTop5ByOrderByVisitasDesc();

  // Obtiene las 5 marcas más populares dentro de una categoría
  @Query("""
      SELECT p.marca FROM Producto p
      WHERE LOWER(p.categoria) = LOWER(:categoria)
      GROUP BY p.marca
      ORDER BY COUNT(p) DESC
      """)
  List<String> findTop5MarcasByCategoria(@Param("categoria") String categoria);

  /*
   * ===========================================================
   * Control de inventarios
   * ===========================================================
   */

  // Contar productos con inventario bajo
  @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock <= :umbral")
  Integer contarInventarioBajo(@Param("umbral") Integer umbral);

  // Listar productos con inventario bajo
  @Query("SELECT p FROM Producto p WHERE p.stock <= :umbral ORDER BY p.stock ASC")
  List<Producto> inventarioBajo(@Param("umbral") Integer umbral);

  /*
   * ===========================================================
   * Filtrado por atributos
   * ===========================================================
   */

  /*
   * Por categoría
   */

  // Productos por categoría
  List<Producto> findByCategoriaIgnoreCase(String categoria);

  // Obtiene todas las marcas de una categoría (sin repetir)
  @Query("SELECT DISTINCT p.marca FROM Producto p WHERE LOWER(p.categoria) = LOWER(:categoria)")
  List<String> findMarcasByCategoria(@Param("categoria") String categoria);

  // Obtiene todas las presentaciones de una categoría (sin repetir)
  @Query("SELECT DISTINCT p.presentacion FROM Producto p WHERE LOWER(p.categoria) = LOWER(:categoria)")
  List<String> findPresentacionesByCategoria(@Param("categoria") String categoria);

  // Obtiene presentaciones en base a marcas
  @Query("""
      SELECT DISTINCT p.presentacion
      FROM Producto p
      WHERE LOWER(p.categoria) = LOWER(:categoria)
        AND (:marca IS NULL OR :marca = '' OR LOWER(p.marca) = LOWER(:marca))
      """)
  List<String> findPresentacionesByCategoriaAndMarca(
      @Param("categoria") String categoria,
      @Param("marca") String marca);

  // Obtiene marcas en base a presentaciones
  @Query("""
      SELECT DISTINCT p.marca
      FROM Producto p
      WHERE LOWER(p.categoria) = LOWER(:categoria)
        AND (:presentacion IS NULL OR :presentacion = '' OR LOWER(p.presentacion) = LOWER(:presentacion))
      """)
  List<String> findMarcasByCategoriaAndPresentacion(
      @Param("categoria") String categoria,
      @Param("presentacion") String presentacion);

  /*
   * Por marca
   */

  // Obtiene presentaciones en base a marcas
  @Query("""
      SELECT DISTINCT p.presentacion
      FROM Producto p
      WHERE (:marca IS NULL OR :marca = '' OR LOWER(p.marca) = LOWER(:marca))
      """)
  List<String> findPresentacionesByMarca(
      @Param("marca") String marca);

  /*
   * Por presentacion
   */

  // Obtiene marcas en base a presentaciones
  @Query("""
      SELECT DISTINCT p.marca
      FROM Producto p
      WHERE (:presentacion IS NULL OR :presentacion = '' OR LOWER(p.presentacion) = LOWER(:presentacion))
      """)
  List<String> findMarcasByPresentacion(
      @Param("presentacion") String presentacion);
}
