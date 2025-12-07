package com.example.botica.controller;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import com.example.botica.service.ProductoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
  private final ProductoService productoService;

  public LoginController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("mostrarModalLogin", true);
    model.addAttribute("productosMasBuscados", productoService.obtenerProductosMasBuscados());
    model.addAttribute("cantidadProductos", 0);
    return "/tienda/inicio";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @GetMapping("/redirectRol")
  public String redirigirSegunRol(Authentication auth) {
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
      return "redirect:/admin/productos";
    } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_VENDEDOR"))) {
      return "redirect:/ventas";
    } else {
      return "redirect:/"; // cliente
    }
  }
}
