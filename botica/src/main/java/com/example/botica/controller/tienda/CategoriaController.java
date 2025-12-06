package com.example.botica.controller.tienda;

import com.example.botica.model.Producto;
import com.example.botica.service.ProductoService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
  private final ProductoService productoService;

  public CategoriaController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/{categoria}")
  public String verPorCategoria(
      @PathVariable String categoria,
      @RequestParam(required = false) String marca,
      @RequestParam(required = false) String presentacion,
      @RequestParam(required = false) Double min,
      @RequestParam(required = false) Double max,
      @RequestParam(required = false) String orden,
      Model model) {

    List<Producto> productos = productoService.filtrarProductos(
        categoria, marca, presentacion, min, max, orden);

    model.addAttribute("categoria", categoria);
    model.addAttribute("categoriaSeleccionada", categoria);

    model.addAttribute("productos", productos);
    model.addAttribute("top5Marcas", productoService.obtenerTopMarcas(categoria));
    model.addAttribute("marcas", productoService.obtenerMarcasPorCategoriaYPresentacion(categoria, presentacion));
    model.addAttribute("presentaciones", productoService.obtenerPresentacionesPorCategoriaYMarca(categoria, marca));

    // Para ver los filtros seleccionados en el HTML
    model.addAttribute("marcaSel", marca);
    model.addAttribute("presSel", presentacion);
    model.addAttribute("minSel", min);
    model.addAttribute("maxSel", max);
    model.addAttribute("ordenSel", orden);

    return "/tienda/categoria";
  }

}
