package com.example.botica.controller.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.botica.model.tienda.Usuario;
import com.example.botica.service.UsuarioService;

@Controller
public class RegistroController {

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("/registro")
  public String mostrarFormularioRegistro(Model model) {
    model.addAttribute("usuario", new Usuario());
    return "tienda/registro"; // Thymeleaf
  }

  @PostMapping("/registro")
  public String procesarRegistro(@ModelAttribute Usuario usuario) {
    String email = usuario.getEmail().toLowerCase();

    // Asignar rol según dominio
    if (email.endsWith("@jhirefarmav.com")) {
      usuario.setRol("VENDEDOR");

    } else if (email.endsWith("@jhirefarmaa.com")) {
      usuario.setRol("ADMIN");

    } else {
      usuario.setRol("CLIENTE");
    }

    // usuario.setRol("CLIENTE");
    // Guardar usuario
    usuarioService.save(usuario);
    // Auto login
    // usuarioService.autoLogin(usuario);
    // Redirigir a la tienda
    return "redirect:/";
  }

}
