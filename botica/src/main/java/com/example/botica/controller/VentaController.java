package com.example.botica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;

import java.util.List;

@Controller
public class VentaController {

  @Autowired
  private ProductoRepository productoRepository;

  @GetMapping("/ventas")
  public String verProductos(Model model) {
    List<Producto> productos = productoRepository.findAll();
    model.addAttribute("productos", productos);
    return "ventas";
  }
}
