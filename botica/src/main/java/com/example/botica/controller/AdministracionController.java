package com.example.botica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministracionController {
  @GetMapping("/administracion")
  public String mostrarInicio() {
    return "administracion";
  }
}
