package com.example.botica.controller.tienda;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.botica.model.tienda.Usuario;
import com.example.botica.repository.tienda.PedidoRepository;
import com.example.botica.repository.tienda.UsuarioRepository;

//(perfil y pedidos)
@Controller
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  private UsuarioRepository usuarioRepo;
  @Autowired
  private PedidoRepository pedidoRepo; // si existe

  @GetMapping("/mi-cuenta")
  public String miCuenta(Model model, Principal principal) {
    Usuario u = usuarioRepo.findByEmail(principal.getName()).orElseThrow();
    model.addAttribute("usuario", u);
    return "cliente/mi-cuenta";
  }

  @GetMapping("/mis-pedidos")
  public String misPedidos(Model model, Principal principal) {
    Usuario u = usuarioRepo.findByEmail(principal.getName()).orElseThrow();
    model.addAttribute("pedidos", pedidoRepo.findById(u.getId()));
    return "cliente/mis-pedidos";
  }
}
