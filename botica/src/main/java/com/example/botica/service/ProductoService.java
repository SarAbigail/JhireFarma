package com.example.botica.service;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

  private final ProductoRepository repo;

  @Autowired
  public ProductoService(ProductoRepository repo) {
    this.repo = repo;
  }

  // Método para guardar productos nuevos
  public Producto guardarProducto(Producto producto) {
    if (producto.getVisitas() == null) {
      producto.setVisitas(0);
    }

    return repo.save(producto);
  }

  public List<Producto> obtenerMasBuscados() {
    return repo.findTop5ByOrderByVisitasDesc();
  }

  public List<Producto> obtenerPorCategoria(String categoria) {
    return repo.findByCategoriaIgnoreCase(categoria);
  }

  public List<String> obtenerTopMarcas(String categoria) {
    return repo.findTop5MarcasByCategoria(categoria);
  }

  public Producto buscarPorId(Long productoId) {
    return repo.findById(productoId).orElse(null);
  }

  public List<Producto> obtenerTodosLosProductos() {
    return repo.findAll();
  }
}
