package com.example.botica.controller;

import com.example.botica.model.Producto;
import com.example.botica.service.CarritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class CarritoGlobal {

  private final CarritoService carritoService;

  public CarritoGlobal(CarritoService carritoService) {
    this.carritoService = carritoService;
  }

  @ModelAttribute("carrito")
  public List<Producto> carrito(HttpSession session) {
    return carritoService.obtenerCarrito(session);
  }
}
