package com.example.botica.controller.tienda;

import com.example.botica.model.Producto;
import com.example.botica.model.tienda.Carrito;
import com.example.botica.model.tienda.Usuario;
import com.example.botica.repository.tienda.UsuarioRepository;
import com.example.botica.service.ProductoService;
import com.example.botica.service.tienda.CarritoService;

import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

  @Autowired
  private ProductoService productoService;

  @Autowired
  private CarritoService carritoService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostMapping("/agregar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void agregarAlCarrito(@RequestParam("productoId") Integer productoId, HttpSession session) {
    Producto producto = productoService.buscarPorId(productoId);
    if (producto != null) {
      carritoService.agregarProducto(session, producto);
    }
  }

  @GetMapping("/fragmento")
  public String fragmentoCarrito(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    return "fragments/tienda/carrito-modal :: carritoFragment";
  }

  // Obtener total del carrito
  @GetMapping("/cantidad")
  @ResponseBody
  public int obtenerCantidad(HttpSession session) {
    return carritoService.obtenerCarrito(session)
        .stream()
        .mapToInt(Producto::getCantidad)
        .sum();
  }

  @PostMapping("/eliminar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminarDelCarrito(@RequestParam("productoId") Integer productoId, HttpSession session) {
    carritoService.eliminarProducto(session, productoId);
  }

  @GetMapping("")
  public String verCarrito(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    return "/tienda/carrito";
  }

  @PostMapping("/aumentar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void aumentarCantidad(@RequestParam Integer productoId, HttpSession session) {
    carritoService.aumentarCantidad(session, productoId);
  }

  @PostMapping("/disminuir")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void disminuirCantidad(@RequestParam Integer productoId, HttpSession session) {
    carritoService.disminuirCantidad(session, productoId);
  }
}
