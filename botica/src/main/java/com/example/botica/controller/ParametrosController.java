package com.example.botica.controller;

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
      } else {
        model.addAttribute("formMarca", new Marca());
      }
    }

    if (tabActiva.equals("sintoma")) {
      if (id != null) {
        model.addAttribute("formSintoma", sintomaService.buscarPorId(id));
      } else {
        model.addAttribute("formSintoma", new Sintoma());
      }
    }

    if (tabActiva.equals("presentacion")) {
      if (id != null) {
        model.addAttribute("formPres", presentacionService.buscarPorId(id));
      } else {
        model.addAttribute("formPres", new Presentacion());
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

  // @GetMapping("/categoria/editar/{id}")
  // public String editarCategoria(@PathVariable int id, Model model) {

  // Categoria cat = categoriaService.buscarPorId(id);

  // model.addAttribute("tabActiva", "categoria");
  // model.addAttribute("cat", cat); // <--- IMPORTANTE
  // model.addAttribute("esEdicion", true); // <--- IMPORTANTE

  // // listas
  // model.addAttribute("categoria", categoriaService.listar());

  // return "parametros";
  // }

}
