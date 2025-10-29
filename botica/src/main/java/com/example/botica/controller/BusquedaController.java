package com.example.botica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.botica.repository.ProductoRepository;

@Controller
public class BusquedaController {

  private ProductoRepository productoRepository;

  @Autowired
  public BusquedaController(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  // Buscar productos por nombre o filtro
  @GetMapping("/busqueda")
  public String buscarProductos(@RequestParam("query") String query, Model model) {
    model.addAttribute("productos", productoRepository.buscarPorVariosCampos(query));
    model.addAttribute("query", query);
    return "busqueda";
  }
}
