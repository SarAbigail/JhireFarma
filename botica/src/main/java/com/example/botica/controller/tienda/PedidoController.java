package com.example.botica.controller.tienda;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.botica.model.Producto;
import com.example.botica.model.tienda.DetallePedido;
import com.example.botica.model.tienda.Pedido;
import com.example.botica.model.tienda.Usuario;
import com.example.botica.service.tienda.CarritoService;
import com.example.botica.service.tienda.ClienteService;
import com.example.botica.service.tienda.DetallePedidoService;
import com.example.botica.service.tienda.PedidoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

  @Autowired
  private CarritoService carritoService;
  @Autowired
  private PedidoService pedidoService;
  @Autowired
  private DetallePedidoService detalleService;
  @Autowired
  private ClienteService clienteService;

  // Mostrar página inicial de reserva
  @GetMapping("")
  public String verReserva(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    model.addAttribute("pedido", new Pedido());
    return "tienda/reserva";
  }

  // Ver reserva usuario
  @GetMapping("/mi_pedido")
  public String miPedido(Model model, HttpSession session) {
    return "tienda/mi_pedido";
  }

  // Ver reserva vendedor
  //// Mostrar lista de reservas
  @GetMapping("/lista")
  public String verPedido(Model model, HttpSession session) {
    model.addAttribute("reservas", pedidoService.listar());
    return "vendedor/lista_pedido";
  }

  //// Mostrar detalle de reservas
  @GetMapping("/detalle/{id}")
  public String verDetallePedido(@PathVariable Integer id, Model model) {
    model.addAttribute("pedido", pedidoService.obtenerPorId(id));
    return "vendedor/lista_pedido_detalle";
  }

  // Acción de "Reservar ahora" del cliente
  @PostMapping("/guardar")
  public String guardarPedido(@ModelAttribute Pedido pedido, HttpSession session, Principal principal) {
    pedido.setFecha(LocalDate.now());
    pedido.setEstado("PENDIENTE");

    // Guardar pedido con o sin usuario
    if (principal != null) {
      // Usuario logueado
      String email = principal.getName();
      Usuario usuario = clienteService.getUsuarioPorEmail(email);
      pedido.setUsuario(usuario);
    } else {
      // Usuario no logueado
      pedido.setUsuario(null);
    }
    // Guardar pedido
    Pedido pedidoGuardado = pedidoService.guardar(pedido);
    // Guardar detalles
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    List<DetallePedido> detalles = new ArrayList<>();
    for (Producto p : carrito) {
      DetallePedido det = new DetallePedido();
      det.setPedido(pedidoGuardado);
      det.setProducto(p);
      det.setCantidad(p.getCantidad());
      det.setPrecioUnitario(p.getPrecio());
      det.setSubtotal(p.getPrecio() * p.getCantidad());
      detalleService.guardar(det);
      detalles.add(det);
    }
    // Guardar pedido completo en sesión solo para invitado
    if (principal == null) {
      session.setAttribute("pedidoInvitado", pedidoGuardado);
      session.setAttribute("detallesInvitado", detalles);
    }
    // Vaciar carrito
    carrito.clear();

    return principal != null ? "redirect:/mi-cuenta" : "redirect:/confirmacion-invitado";
    // // Guardar pedido
    // Pedido pedidoGuardado = pedidoService.guardar(pedido);
    // // Convertir carrito a detalles
    // List<Producto> carrito = carritoService.obtenerCarrito(session);
    // carrito.forEach(p -> {
    // DetallePedido det = new DetallePedido();
    // det.setPedido(pedidoGuardado);
    // // det.setProductoId(p.getId());
    // det.setProducto(p);
    // det.setCantidad(p.getCantidad());
    // det.setPrecioUnitario(p.getPrecio());
    // det.setSubtotal(p.getPrecio() * p.getCantidad());
    // detalleService.guardar(det);
    // });
    // // Vaciar carrito
    // carrito.clear();

    // // Redirigir según si está logueado o no
    // if (principal != null) {
    // return "redirect:/mi-cuenta"; // usuario logueado
    // } else {
    // return "redirect:/confirmacion-invitado"; // usuario invitado
    // }
  }
}