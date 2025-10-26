package com.example.botica.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaController {
      @GetMapping("/tienda")
    public String mostrarInicio() {
        return "tienda";
    }
}
