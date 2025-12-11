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
    if (producto != null) {
      // Aumentar visitas
      producto.setVisitas(producto.getVisitas() + 1);
      productoService.guardarProducto(producto);
    }
    model.addAttribute("producto", producto);
    return "tienda/producto";
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