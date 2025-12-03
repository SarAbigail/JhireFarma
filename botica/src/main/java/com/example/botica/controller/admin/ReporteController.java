package com.example.botica.controller.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.botica.service.admin.ReporteService;

@Controller
@RequestMapping("/reportes/ventas")
public class ReporteController {
  @Autowired
  private ReporteService reporteService;

  @GetMapping
  public String reporteGeneral(
      @RequestParam(name = "periodo", required = false, defaultValue = "semana") String periodo,
      Model model) {
    LocalDate hoy = LocalDate.now();
    LocalDate inicio;
    LocalDate fin = hoy;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    switch (periodo.toLowerCase()) {
      case "dia":
        inicio = hoy;
        break;
      case "mes":
        inicio = hoy.withDayOfMonth(1);
        break;
      case "año":
        inicio = hoy.withDayOfYear(1);
        break;
      default: // semana
        inicio = hoy.minusDays(6);
    }
    // kpis
    model.addAttribute("ingresosTotales", reporteService.ingresosTotales(inicio, fin));
    model.addAttribute("cantidadVentas", reporteService.cantidadVentas(inicio, fin));
    model.addAttribute("inventarioBajo", reporteService.inventarioBajo(10));
    model.addAttribute("inventarioBajoDetalle", reporteService.inventarioBajoDetalle(10));
    // Rango de fechas para mostrar en KPIs
    model.addAttribute("fechaInicioStr", inicio.format(formatter));
    model.addAttribute("fechaFinStr", fin.format(formatter));
    // Datos para gráfico
    Map<String, Double> ingresosPorFecha = reporteService.ventasEntreFechas(inicio, fin);
    Map<String, Long> cantidadPorFecha = reporteService.cantidadVentasPorFechaEntreFechas(inicio, fin);
    // Convertir mapas a listas ordenadas
    List<String> fechas = new ArrayList<>(ingresosPorFecha.keySet());
    List<Double> ingresos = new ArrayList<>(ingresosPorFecha.values());
    List<Long> cantidades = new ArrayList<>(cantidadPorFecha.values());

    // Evitar null
    ingresos.replaceAll(v -> v == null ? 0.0 : v);
    cantidades.replaceAll(v -> v == null ? 0L : v);

    // Pasar a modelo
    model.addAttribute("fechas", fechas);
    model.addAttribute("ingresos", ingresos);
    model.addAttribute("cantidades", cantidades);

    // Otros datos del dashboard
    model.addAttribute("ventasUltimaSemana", new HashMap<String, Double>());
    model.addAttribute("ventasUltimaSemana", reporteService.ventasEntreFechas(inicio, fin));
    model.addAttribute("topProductos", reporteService.productosMasVendidosEntreFechas(inicio, fin)
        .stream()
        .limit(10)
        .collect(Collectors.toMap(o -> (String) o[0], o -> ((Long) o[1]).intValue())));
    model.addAttribute("totalVentas", reporteService.totalVentasEntreFechas(inicio, fin));
    // //Grafico más vendidos + menos vendidos
    model.addAttribute("masVendidos", reporteService.productosMasVendidosEntreFechas(inicio, fin));
    model.addAttribute("menosVendidos", reporteService.productosMenosVendidosEntreFechas(inicio, fin));
    model.addAttribute("periodoSeleccionado", periodo);

    return "admin/dashboard";
  }
}