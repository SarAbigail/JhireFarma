package com.example.botica.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.botica.model.Producto;

import jakarta.servlet.http.HttpSession;

@Service
public class CarritoService {
  //
  private static final String SESSION_CARRITO = "carrito";

  public void agregarProducto(HttpSession session, Producto producto) {
    @SuppressWarnings("unchecked")
    List<Producto> carrito = (List<Producto>) session.getAttribute(SESSION_CARRITO);
    if (carrito == null)
      carrito = new ArrayList<>();

    // Si ya está, aumenta cantidad
    boolean encontrado = false;
    for (Producto p : carrito) {
      if (p.getId().equals(producto.getId())) {
        p.setCantidad(p.getCantidad() + 1);
        encontrado = true;
        break;
      }
    }
    if (!encontrado) {
      Producto pc = new Producto();
      pc.setId(producto.getId());
      pc.setNombre(producto.getNombre());
      pc.setPresentacion(producto.getPresentacion());
      pc.setImagen(producto.getImagen());
      pc.setPrecio(producto.getPrecio());
      pc.setCantidad(1);
      carrito.add(pc);
    }

    session.setAttribute(SESSION_CARRITO, carrito);
  }

  public List<Producto> obtenerCarrito(HttpSession session) {
    @SuppressWarnings("unchecked")
    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
    return (carrito != null) ? carrito : new ArrayList<>();
  }

  public double calcularTotal(HttpSession session) {
    return obtenerCarrito(session).stream()
        .mapToDouble(p -> p.getPrecio() * p.getCantidad())
        .sum();
  }

  // Eliminar producto del carrito
  public void eliminarProducto(HttpSession session, Long productoId) {
    List<Producto> carrito = obtenerCarrito(session);
    carrito.removeIf(p -> p.getId().equals(Long.valueOf(productoId)));
  }

  public void aumentarCantidad(HttpSession session, Long productoId) {
    List<Producto> carrito = obtenerCarrito(session);
    for (Producto p : carrito) {
      if (p.getId().equals(productoId)) {
        p.setCantidad(p.getCantidad() + 1);
        break;
      }
    }
  }

  public void disminuirCantidad(HttpSession session, Long productoId) {
    List<Producto> carrito = obtenerCarrito(session);
    Iterator<Producto> iter = carrito.iterator();
    while (iter.hasNext()) {
      Producto p = iter.next();
      if (p.getId().equals(productoId)) {
        if (p.getCantidad() > 1) {
          p.setCantidad(p.getCantidad() - 1);
        } else {
          iter.remove(); // si llega a 0 se elimina
        }
        break;
      }
    }
  }
}
