package com.example.botica.controller;

import com.example.botica.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

  private final ProductoService productoService;

  public InicioController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/")
  public String inicio(Model model) {
    // Agregar los 5 productos más buscados
    model.addAttribute("productosMasBuscados", productoService.obtenerMasBuscados());
    model.addAttribute("cantidadProductos", 0);
    return "inicio";
  }
}
