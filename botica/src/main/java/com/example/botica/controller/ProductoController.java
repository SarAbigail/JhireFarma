package com.example.botica.controller;

import com.example.botica.model.Producto;
import com.example.botica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  // Mostrar detalle de un producto
  @GetMapping("/{id}")
  public String detalle(@PathVariable Integer id, Model model) {
    Producto producto = productoService.buscarPorId(id);
    if (producto == null) {
      return "redirect:/busqueda";
    }
    model.addAttribute("producto", producto);
    return "/tienda/producto";
  }

  // ------------------------------------------
  // FORMULARIO PARA AGREGAR UN PRODUCTO (GET)
  // ------------------------------------------
  @GetMapping("/nuevo")
  public String mostrarFormulario(Model model) {
    model.addAttribute("producto", new Producto());
    return "producto-form";
  }

}

// Con esto funcionaba
// @Controller
// public class ProductoController {

// @Autowired
// private ProductoRepository productoRepository;

// // Mostrar detalle de un producto
// @GetMapping("/producto/{id}")
// public String detalle(@PathVariable Long id, Model model) {
// Producto producto = productoRepository.findById(id).orElse(null);
// if (producto == null) {
// return "redirect:/busqueda";
// }
// model.addAttribute("producto", producto);
// return "producto";
// }

// eEjemplo para ser presentado en dcumento
// @GetMapping("/producto/{id}")
// public String detalle(@PathVariable Long id, Model model) {

// // Validar que ID sea positivo
// if (id == null || id <= 0) {
// return "redirect:/busqueda?error=id_invalido";
// }

// Optional<Producto> optionalProducto = productoRepository.findById(id);
// if (optionalProducto.isEmpty()) {
// return "redirect:/busqueda?error=no_encontrado";
// }

// model.addAttribute("producto", optionalProducto.get());
// return "producto";
// }
