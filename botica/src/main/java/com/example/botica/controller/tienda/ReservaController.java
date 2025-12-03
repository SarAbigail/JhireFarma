package com.example.botica.controller.tienda;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.botica.model.Producto;
import com.example.botica.model.tienda.DetallePedido;
import com.example.botica.model.tienda.Pedido;
import com.example.botica.service.CarritoService;
import com.example.botica.service.tienda.DetallePedidoService;
import com.example.botica.service.tienda.PedidoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

  @Autowired
  private CarritoService carritoService;
  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private DetallePedidoService detalleService;

  // Mostrar página de reserva
  @GetMapping("")
  public String verReserva(Model model, HttpSession session) {
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    model.addAttribute("carrito", carrito);
    model.addAttribute("total", carritoService.calcularTotal(session));
    model.addAttribute("pedido", new Pedido());
    return "tienda/reserva";
  }

  // Acción de "Reservar ahora"
  // @PostMapping("")
  // public String reservarPedido(@RequestParam String nombreRecoge,
  // @RequestParam String horario,
  // HttpSession session,
  // Model model) {
  // List<Producto> carrito = carritoService.obtenerCarrito(session);

  // // Limpiar carrito después de reservar
  // carrito.clear();

  // model.addAttribute("mensaje", "¡Pedido reservado con éxito!");
  // return "reserva-confirmacion"; // nueva página de confirmación
  // }

  // para ver si funciona

  @PostMapping("/guardar")
  public String guardar(@ModelAttribute Pedido pedido, HttpSession session) {

    pedido.setFecha(LocalDate.now());
    pedido.setEstado("PENDIENTE");

    // Guardar pedido
    Pedido pedidoGuardado = pedidoService.guardar(pedido);

    // Convertir carrito a detalles
    List<Producto> carrito = carritoService.obtenerCarrito(session);
    carrito.forEach(p -> {
      DetallePedido det = new DetallePedido();
      det.setPedido(pedidoGuardado);
      det.setProductoId(p.getId());
      det.setCantidad(p.getCantidad());
      det.setPrecioUnitario(p.getPrecio());
      det.setSubtotal(p.getPrecio() * p.getCantidad());
      detalleService.guardar(det);
    });

    // Vaciar carrito
    carrito.clear();

    return "redirect:/reserva/lista";
  }

  @GetMapping("/lista")
  public String lista(Model model) {
    model.addAttribute("reservas", pedidoService.listar());
    return "reserva/lista";
  }
}
