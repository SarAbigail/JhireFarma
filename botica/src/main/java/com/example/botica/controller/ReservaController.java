package com.example.botica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.botica.model.Producto;
import com.example.botica.service.CarritoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

  @Autowired
  private CarritoService carritoService;

  // Mostrar página de reserva
  @GetMapping("")
  public String verReserva(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    return "reserva";
  }

  // Acción de "Reservar ahora"
  @PostMapping("")
  public String reservarPedido(@RequestParam String nombreRecoge,
      @RequestParam String horario,
      HttpSession session,
      Model model) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);

    // Limpiar carrito después de reservar
    carrito.clear();

    model.addAttribute("mensaje", "¡Pedido reservado con éxito!");
    return "reserva-confirmacion"; // nueva página de confirmación
  }
}
