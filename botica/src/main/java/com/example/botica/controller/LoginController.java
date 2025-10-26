package com.example.botica.controller;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/redirectRol")
  public String redirigirSegunRol(Authentication auth) {
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
      return "redirect:/administracion";
    } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_VENDEDOR"))) {
      return "redirect:/ventas";
    } else {
      return "redirect:/"; // cliente
    }
  }
}
