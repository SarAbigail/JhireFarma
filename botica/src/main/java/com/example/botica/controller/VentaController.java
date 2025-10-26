package com.example.botica.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Controller
public class VentaController {
    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        List<String> ventas = List.of("Venta 1", "Venta 2", "Venta 3");
        model.addAttribute("ventas", ventas);
        return "ventas";
    }
}
