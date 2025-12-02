package com.example.botica.controller.vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.botica.model.Producto;
import com.example.botica.model.Venta;
import com.example.botica.repository.ProductoRepository;
import com.example.botica.service.vendedor.VentaService;

import java.util.List;

@Controller
public class VentaController {

  @Autowired
  private ProductoRepository productoRepository;
  @Autowired
  private VentaService ventaService;

  @GetMapping("/ventas/ñ")
  public String verProductos(Model model) {
    List<Producto> productos = productoRepository.findAll();
    model.addAttribute("productos", productos);
    return "vendedor/ventas";
  }

  @GetMapping("/ventas")
  public String nuevaVenta(Model model) {
    Venta venta = ventaService.prepararNuevaVenta();
    model.addAttribute("venta", venta);
    List<Producto> productos = productoRepository.findAll();
    model.addAttribute("productos", productos);
    return "vendedor/ventas";
  }

  @GetMapping("/vendedor/reservas")
  public String reservas(Model model) {

    return "vendedor/reservas";
  }
}
