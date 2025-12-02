package com.example.botica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.botica.service.ReporteService;

@Controller
@RequestMapping("/reportes/ventas")
public class ReporteController {

  @Autowired
  private ReporteService reporteService;

  @GetMapping
  public String reporteGeneral(Model model) {

    // model.addAttribute("ventasDiarias", reporteService.ventasDiarias());
    // model.addAttribute("ventasSemanales", reporteService.ventasSemanales());
    // model.addAttribute("ventasMensuales", reporteService.ventasMensuales());
    // model.addAttribute("ventasAnuales", reporteService.ventasAnuales());

    // model.addAttribute("masVendidos", reporteService.productosMasVendidos());
    // model.addAttribute("menosVendidos", reporteService.productosMenosVendidos());

    // model.addAttribute("totalVentas", reporteService.totalVentas());

    return "admin/reportes/ventas";
  }
}