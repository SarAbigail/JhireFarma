package com.example.botica.controller.vendedor;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

  @Autowired
  private ProductoRepository productoRepository;

  // Devuelve todos los productos o filtrados
  // @GetMapping
  // public List<Producto> listarProductos(@RequestParam(name = "buscar", required
  // = false) String buscar) {
  // if (buscar != null && !buscar.isEmpty()) {
  // return productoRepository.buscarPorVariosCampos(buscar);
  // }
  // return productoRepository.findAll();
  // }
  // Devuelve todos los productos o los filtrados por el parámetro "buscar"
  @GetMapping
  public List<Producto> listarOBuscar(@RequestParam(name = "buscar", required = false) String buscar) {
    if (buscar != null && !buscar.isEmpty()) {
      return productoRepository.buscarPorVariosCampos(buscar);
    }
    return productoRepository.findAll();
  }
}
