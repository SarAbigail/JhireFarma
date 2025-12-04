package com.example.botica.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.botica.model.*;
import com.example.botica.service.*;

@Controller
@RequestMapping("/parametros")
public class ParametrosController {

  @Autowired
  private CategoriaService categoriaService;
  @Autowired
  private MarcaService marcaService;
  @Autowired
  private PresentacionService presentacionService;
  @Autowired
  private SintomaService sintomaService;

  @GetMapping
  public String parametros(
      Model model,
      @RequestParam(value = "tab", defaultValue = "categoria") String tabActiva,
      @RequestParam(value = "id", required = false) Integer id) {

    model.addAttribute("categoria", categoriaService.listar());
    model.addAttribute("marca", marcaService.listar());
    model.addAttribute("presentacion", presentacionService.listar());
    model.addAttribute("sintoma", sintomaService.listar());

    // objeto para el form
    if (tabActiva.equals("categoria")) {
      if (id != null) {
        model.addAttribute("formCategoria", categoriaService.buscarPorId(id));
        model.addAttribute("esEdicion", true);
      } else {
        model.addAttribute("formCategoria", new Categoria());
        model.addAttribute("esEdicion", false);
      }
    }

    if (tabActiva.equals("marca")) {
      if (id != null) {
        model.addAttribute("formMarca", marcaService.buscarPorId(id));
        model.addAttribute("esEdicion", true);

      } else {
        model.addAttribute("formMarca", new Marca());
        model.addAttribute("esEdicion", false);
      }
    }

    if (tabActiva.equals("sintoma")) {
      if (id != null) {
        model.addAttribute("formSintoma", sintomaService.buscarPorId(id));
        model.addAttribute("esEdicion", true);
      } else {
        model.addAttribute("formSintoma", new Sintoma());
        model.addAttribute("esEdicion", false);
      }
    }

    if (tabActiva.equals("presentacion")) {
      if (id != null) {
        model.addAttribute("formPresentacion", presentacionService.buscarPorId(id));
        model.addAttribute("esEdicion", true);
      } else {
        model.addAttribute("formPresentacion", new Presentacion());
        model.addAttribute("esEdicion", false);
      }
    }

    model.addAttribute("tabActiva", tabActiva);

    return "parametros";
  }

  // --------- GUARDAR CATEGORIA ---------
  @PostMapping("/categoria/guardar")
  public String guardarCategoria(Categoria categoria) {
    categoriaService.guardar(categoria);
    return "redirect:/parametros?tab=categoria";
  }

  // --------- GUARDAR MARCA ---------
  @PostMapping("/marca/guardar")
  public String guardarMarca(Marca marca) {
    marcaService.guardar(marca);
    return "redirect:/parametros?tab=marca";
  }

  // --------- GUARDAR PRESENTACION ---------
  @PostMapping("/presentacion/guardar")
  public String guardarPresentacion(Presentacion p) {
    presentacionService.guardar(p);
    return "redirect:/parametros?tab=presentacion";
  }

  // --------- GUARDAR SINTOMA ---------
  @PostMapping("/sintoma/guardar")
  public String guardarSintoma(Sintoma s) {
    sintomaService.guardar(s);
    return "redirect:/parametros?tab=sintoma";
  }

  // ---------- TOGGLE ----------
  @GetMapping("/categoria/toggle/{id}")
  public String toggleCategoria(@PathVariable Integer id) {
    Categoria categoria = categoriaService.buscarPorId(id);
    if (categoria != null) {
      categoria.setEstado(!categoria.getEstado());
      categoriaService.guardar(categoria);
    }
    return "redirect:/parametros?tab=categoria";
  }

  // ---------- TOGGLE ----------
  @GetMapping("/marca/toggle/{id}")
  public String toggleMarca(@PathVariable Integer id) {
    Marca marca = marcaService.buscarPorId(id);
    if (marca != null) {
      marca.setEstado(!marca.getEstado());
      marcaService.guardar(marca);
    }
    return "redirect:/parametros?tab=marca";
  }

  // ---------- TOGGLE ----------
  @GetMapping("/sintoma/toggle/{id}")
  public String toggleSintoma(@PathVariable Integer id) {
    Sintoma sintoma = sintomaService.buscarPorId(id);
    if (sintoma != null) {
      sintoma.setEstado(!sintoma.getEstado());
      sintomaService.guardar(sintoma);
    }
    return "redirect:/parametros?tab=sintoma";
  }

  // ---------- TOGGLE ----------
  @GetMapping("/presentacion/toggle/{id}")
  public String togglePresentacion(@PathVariable Integer id) {
    Presentacion presentacion = presentacionService.buscarPorId(id);
    if (presentacion != null) {
      presentacion.setEstado(!presentacion.getEstado());
      presentacionService.guardar(presentacion);
    }
    return "redirect:/parametros?tab=presentacion";
  }

}
