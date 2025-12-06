package com.example.botica.service;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

  private final ProductoRepository productoRepository;

  @Autowired
  public ProductoService(ProductoRepository repo) {
    this.productoRepository = repo;
  }

  /*
   * ===========================================================
   * CRUD
   * ===========================================================
   */

  // Método para guardar productos nuevos
  public Producto guardarProducto(Producto producto) {
    if (producto.getVisitas() == null) {
      producto.setVisitas(0);
    }
    return productoRepository.save(producto);
  }

  public Producto eliminarProducto(Integer id) {
    Producto prod = productoRepository.findById(id).orElse(null);
    if (prod != null) {
      productoRepository.deleteById(id);
    }
    return prod;
  }

  /*
   * ===========================================================
   * Los destacados
   * ===========================================================
   */

  // Obtener productos más buscados
  public List<Producto> obtenerProductosMasBuscados() {
    return productoRepository.findTop5ByOrderByVisitasDesc();
  }

  // Obtiene las 5 marcas más top
  public List<String> obtenerTopMarcas(String categoria) {
    return productoRepository.findTop5MarcasByCategoria(categoria);
  }

  /*
   * ===========================================================
   * Búsquedas
   * ===========================================================
   */

  // Buscar productos por ID
  public Producto buscarPorId(Integer productoId) {
    return productoRepository.findById(productoId).orElse(null);
  }

  // Obtener todos los productos
  public List<Producto> obtenerTodosLosProductos() {
    return productoRepository.findAll();
  }

  // Obtener marcas de productos
  public List<String> obtenerMarcasDeProductos(List<Producto> productos) {
    return productos.stream()
        .map(Producto::getMarca)
        .filter(m -> m != null && !m.isBlank())
        .distinct()
        .sorted()
        .toList();
  }

  // Obtener presentaciones de productos
  public List<String> obtenerPresentacionesDeProductos(List<Producto> productos) {
    return productos.stream()
        .map(Producto::getPresentacion)
        .filter(p -> p != null && !p.isBlank())
        .distinct()
        .sorted()
        .toList();
  }

  /*
   * ===========================================================
   * Filtrado por atributos
   * ===========================================================
   */

  /*
   * Por categoría
   */

  // Obtener los productos
  public List<Producto> obtenerProductosPorCategoria(String categoria) {
    return productoRepository.findByCategoriaIgnoreCase(categoria);
  }

  // Obtener las presentaciones
  public List<String> obtenerPresentacionesPorCategoria(String categoria) {
    return productoRepository.findPresentacionesByCategoria(categoria);
  }

  // Obtener marcas y presentaciones
  public List<String> obtenerPresentacionesPorCategoriaYMarca(String categoria, String marca) {
    return productoRepository.findPresentacionesByCategoriaAndMarca(categoria, marca);
  }

  // Obtener marcas y presentaciones
  public List<String> obtenerMarcasPorCategoriaYPresentacion(String categoria, String marca) {
    return productoRepository.findMarcasByCategoriaAndPresentacion(categoria, marca);
  }

  // Obtener las marcas
  public List<String> obtenerMarcasPorCategoria(String categoria) {
    return productoRepository.findMarcasByCategoria(categoria);
  }

  // Filtros dinámicos pero en base a categoría
  public List<Producto> filtrarProductos(
      String categoria,
      String marca,
      String presentacion,
      Double min,
      Double max,
      String orden) {

    List<Producto> lista = productoRepository.findByCategoriaIgnoreCase(categoria);

    // Filtro por marca
    if (marca != null && !marca.isEmpty()) {
      lista = lista.stream()
          .filter(p -> marca.equalsIgnoreCase(p.getMarca()))
          .toList();
    }

    // Filtro por presentación
    if (presentacion != null && !presentacion.isEmpty()) {
      lista = lista.stream()
          .filter(p -> presentacion.equalsIgnoreCase(p.getPresentacion()))
          .toList();
    }

    // Filtro de precio mínimo
    if (min != null) {
      lista = lista.stream()
          .filter(p -> p.getPrecio() >= min)
          .toList();
    }

    // Filtro de precio máximo
    if (max != null) {
      lista = lista.stream()
          .filter(p -> p.getPrecio() <= max)
          .toList();
    }

    // Ordenamiento
    if ("asc".equals(orden)) {
      lista = lista.stream()
          .sorted((a, b) -> a.getPrecio().compareTo(b.getPrecio()))
          .toList();
    } else if ("desc".equals(orden)) {
      lista = lista.stream()
          .sorted((a, b) -> b.getPrecio().compareTo(a.getPrecio()))
          .toList();
    }

    return lista;
  }

  /*
   * Por marca
   */

  // Obtener presentacion por marca
  public List<String> obtenerPresentacionesPorMarca(String marca) {
    return productoRepository.findPresentacionesByMarca(marca);
  }

  /*
   * Por presentacion
   */

  // Obtener marcas por presentacion
  public List<String> obtenerMarcasPorPresentacion(String marca) {
    return productoRepository.findMarcasByPresentacion(marca);
  }

  /*
   * Dinámicos
   */

  public List<Producto> buscarYFiltrarProductos(
      String query, String marca, String presentacion,
      Double min, Double max, String orden) {

    List<Producto> base = productoRepository.buscarPorVariosCampos(query);

    return base.stream()
        .filter(p -> marca == null || marca.isEmpty() || p.getMarca().equals(marca))
        .filter(p -> presentacion == null || presentacion.isEmpty() || p.getPresentacion().equals(presentacion))
        .filter(p -> min == null || p.getPrecio() >= min)
        .filter(p -> max == null || p.getPrecio() <= max)
        .sorted((a, b) -> {
          if ("asc".equals(orden))
            return a.getPrecio().compareTo(b.getPrecio());
          if ("desc".equals(orden))
            return b.getPrecio().compareTo(a.getPrecio());
          return 0;
        })
        .toList();
  }

}
