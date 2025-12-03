package com.example.botica.controller.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.botica.service.tienda.PedidoService;

@Controller
@RequestMapping("/reservas")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping
  public String listarReservas(Model model) {
    model.addAttribute("reservas", pedidoService.listar());
    return "reservas/lista";
  }

  @GetMapping("/{id}")
  public String verDetalle(@PathVariable Integer id, Model model) {
    model.addAttribute("pedido", pedidoService.obtenerPorId(id));
    return "reservas/detalle";
  }
}
