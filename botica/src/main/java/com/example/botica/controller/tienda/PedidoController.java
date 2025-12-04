package com.example.botica.controller.tienda;

import java.time.LocalDate;
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
import com.example.botica.service.tienda.CarritoService;
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

  // Acción de "Reservar ahora"
  @PostMapping("/guardar")
  public String guardarPedido(@ModelAttribute Pedido pedido, HttpSession session) {
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
    return "redirect:/pedido/mi_pedido";
  }
}