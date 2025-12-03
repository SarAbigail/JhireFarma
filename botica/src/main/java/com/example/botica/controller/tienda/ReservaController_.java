package com.example.botica.controller.tienda;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.botica.model.tienda.Pedido;
import com.example.botica.service.tienda.DetallePedidoService;
import com.example.botica.service.tienda.PedidoService;

@Controller
@RequestMapping("/reservas")
public class ReservaController_ {

  // @Autowired
  // private PedidoService pedidoService;

  // @Autowired
  // private DetallePedidoService detalleService;

  // @PostMapping("/guardar")
  // public String guardar(@ModelAttribute Pedido pedido) {

  // pedido.setFecha(LocalDate.now());
  // pedido.setEstado("PENDIENTE");

  // // Guarda el pedido
  // Pedido pedidoGuardado = pedidoService.guardar(pedido);

  // // Si necesitas guardar detalles (te doy un ejemplo)
  // // Aquí normalmente viene de un carrito o formulario
  // /*
  // * for(DetallePedido det : pedido.getDetalles()) {
  // * det.setPedido(pedidoGuardado);
  // * detalleService.guardar(det);
  // * }
  // */

  // return "redirect:/reservas/lista";
  // }

  // @GetMapping("/lista")
  // public String lista(Model model) {
  // model.addAttribute("reservas", pedidoService.listar());
  // return "reservas/lista";
  // }
}
