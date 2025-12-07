package com.example.botica.controller.tienda;

import com.example.botica.model.Producto;
import com.example.botica.model.tienda.Usuario;
import com.example.botica.service.UsuarioService;
import com.example.botica.service.tienda.CarritoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class CarritoGlobal {

  private final CarritoService carritoService;
  private final UsuarioService usuarioService;

  public CarritoGlobal(CarritoService carritoService, UsuarioService usuarioService) {
    this.carritoService = carritoService;
    this.usuarioService = usuarioService;
  }

  @ModelAttribute("carrito")
  public List<Producto> carrito(HttpSession session) {
    return carritoService.obtenerCarrito(session);
  }

  @ModelAttribute("carritoCantidadTotal")
  public int carritoCantidadTotal(HttpSession session) {
    return carritoService.obtenerCarrito(session)
        .stream()
        .mapToInt(Producto::getCantidad)
        .sum();
  }

  @ModelAttribute
  public void datosUsuarioLogueado(Model model, Authentication auth) {
    if (auth != null) {
      String email = auth.getName(); // el username = email
      Usuario usuario = usuarioService.findByEmail(email);

      if (usuario != null) {
        model.addAttribute("usuarioLogueado", usuario);
      }
    }
  }
}