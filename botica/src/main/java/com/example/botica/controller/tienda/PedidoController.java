package com.example.botica.controller.tienda;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  // -------------------------
  // ADMIN
  // -------------------------

  // -------------------------
  // TIENDA
  // -------------------------

  // MOSTRAR PÁGINA CON DATOS PARA CREAR LA RESERVA
  @GetMapping("")
  public String verReserva(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    model.addAttribute("pedido", new Pedido());
    return "tienda/reserva";
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
  }
  // -------------------------
  // VENDEDOR
  // -------------------------

  // MOSTRAR LISTADO DE RESERVAS
  @GetMapping("/lista")
  public String listarPedidos(
      @RequestParam(required = false) String estado,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
      Model model) {

    List<Pedido> pedidos = pedidoService.listarConFiltrosEstadoYRangoFecha(estado, fechaInicio, fechaFin);

    model.addAttribute("reservas", pedidos);
    return "vendedor/lista_pedido";
  }

  // MOSTRAR DETALLE DE RESERVAS
  @GetMapping("/detalle/{id}")
  public String verDetallePedido(@PathVariable Integer id, Model model) {
    model.addAttribute("pedido", pedidoService.obtenerPorId(id));
    return "vendedor/lista_pedido_detalle";
  }

  // CAMBIAR ESTADO DE RESERVAS
  @PostMapping("/cambiarEstado/{id}")
  public String cambiarEstado(
      @PathVariable Integer id,
      @RequestParam("nuevoEstado") String nuevoEstado) {
    pedidoService.cambiarEstadoReservaPedido(id, nuevoEstado);
    return "redirect:/pedido/lista?success=EstadoActualizado";
  }
}