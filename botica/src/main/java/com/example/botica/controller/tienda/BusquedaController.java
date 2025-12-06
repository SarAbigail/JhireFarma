package com.example.botica.controller.tienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;
import com.example.botica.service.ProductoService;

@Controller
public class BusquedaController {

  private ProductoRepository productoRepository;

  private ProductoService productoService;

  @Autowired
  public BusquedaController(ProductoRepository productoRepository, ProductoService productoService) {
    this.productoRepository = productoRepository;
    this.productoService = productoService;
  }

  // Buscar productos por nombre o filtro
  @GetMapping("/busqueda")
  public String buscarProductos(
      @RequestParam("query") String query,
      @RequestParam(required = false) String marca,
      @RequestParam(required = false) String presentacion,
      @RequestParam(required = false) Double min,
      @RequestParam(required = false) Double max,
      @RequestParam(required = false) String orden,
      Model model) {

    // Buscar productos filtrados por query + filtros
    List<Producto> productos = productoService.buscarYFiltrarProductos(
        query, marca, presentacion, min, max, orden);

    // Filtrar dinamicamente basado en el resultado de búsqueda
    List<String> marcas = productoService.obtenerMarcasDeProductos(productos);
    List<String> presentaciones = productoService.obtenerPresentacionesDeProductos(productos);

    model.addAttribute("productos", productos);
    model.addAttribute("query", query);
    model.addAttribute("marcas", marcas);
    model.addAttribute("presentaciones", presentaciones);

    // Para ver los filtros seleccionados en el HTML
    model.addAttribute("marcaSel", marca);
    model.addAttribute("presSel", presentacion);
    model.addAttribute("minSel", min);
    model.addAttribute("maxSel", max);
    model.addAttribute("ordenSel", orden);
    return "/tienda/busqueda";
  }
}
