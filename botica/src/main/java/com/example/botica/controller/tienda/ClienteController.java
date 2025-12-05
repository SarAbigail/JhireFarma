package com.example.botica.controller.tienda;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.botica.model.tienda.DetallePedido;
import com.example.botica.model.tienda.Pedido;
import com.example.botica.model.tienda.Usuario;
import com.example.botica.repository.tienda.PedidoRepository;
import com.example.botica.repository.tienda.UsuarioRepository;
import com.example.botica.service.tienda.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/mi-cuenta")
  public String perfil(Model model, Principal principal) {
    String email = principal.getName();
    model.addAttribute("usuario", clienteService.getUsuarioPorEmail(email));
    model.addAttribute("pedidos", clienteService.getPedidosUsuario(email));
    return "tienda/mi_perfil"; // Thymeleaf
  }

  @PostMapping("/perfil/editar")
  public String editarPerfil(@ModelAttribute Usuario usuario) {
    clienteService.actualizarDatosUsuario(usuario);
    return "redirect:/mi-cuenta";
  }

  @GetMapping("/confirmacion-invitado")
  public String confirmacionInvitado(HttpSession session, Model model) {
    Pedido pedido = (Pedido) session.getAttribute("pedidoInvitado");
    List<DetallePedido> detalles = (List<DetallePedido>) session.getAttribute("detallesInvitado");

    if (pedido == null) {
      // Si no hay pedido en sesión, redirigir al inicio
      return "redirect:/";
    }

    model.addAttribute("pedido", pedido);
    model.addAttribute("detalles", detalles);

    return "tienda/confirmacion_invitado"; // Thymeleaf
  }

}
