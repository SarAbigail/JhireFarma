package com.example.botica.controller;

import com.example.botica.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

  private final ProductoService productoService;

  public CategoriaController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/{nombre}")
  public String verPorCategoria(@PathVariable String nombre, Model model) {
    model.addAttribute("categoria", nombre);

    model.addAttribute("categoriaSeleccionada", nombre);

    model.addAttribute("productos", productoService.obtenerPorCategoria(nombre));
    model.addAttribute("marcas", productoService.obtenerTopMarcas(nombre));
    return "categoria";
  }
}
