package com.example.botica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.botica.model.Producto;
import com.example.botica.service.ProductoService;

// @Controller
// public class AdminController {
//   @GetMapping("/administracion")
//   public String mostrarInicio() {
//     return "administracion";
//   }
// }

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ProductoService productoService;

  // // Vista principal del módulo admin
  // @GetMapping
  // public String panelAdmin() {
  // return "admin/index"; // puedes crear un menú o panel principal
  // }

  // Listar productos
  @GetMapping("/productos")
  public String listarProductos(Model model) {
    model.addAttribute("productos", productoService.obtenerTodosLosProductos());
    return "listar_productos";
  }

  // Formulario nuevo producto
  @GetMapping("/productos/nuevo")
  public String nuevoProducto(Model model) {
    model.addAttribute("producto", new Producto());
    return "form_producto";
  }

  // // Guardar producto
  // @PostMapping("/productos/guardar")
  // public String guardarProducto(@ModelAttribute Producto producto,
  // @RequestParam(value = "imagenFile", required = false) MultipartFile
  // imagenFile,
  // RedirectAttributes ra) {
  // try {
  // productoService.guardar(producto, imagenFile);
  // ra.addFlashAttribute("success", "Producto guardado correctamente");
  // } catch (Exception e) {
  // ra.addFlashAttribute("error", "Error al guardar el producto");
  // }
  // return "redirect:/admin/productos";
  // }

  // Editar producto
  @GetMapping("/productos/editar/{id}")
  public String editarProducto(@PathVariable Long id, Model model) {
    Producto producto = productoService.buscarPorId(id);
    if (producto == null) {
      // Si no existe, redirige al listado con mensaje de error
      return "redirect:/admin/productos?error=notfound";
    }

    model.addAttribute("producto", producto);
    return "form_producto";
  }

  // // Eliminar producto
  // @PostMapping("/productos/eliminar/{id}")
  // public String eliminarProducto(@PathVariable Long id, RedirectAttributes ra)
  // {
  // productoService.eliminar(id);
  // ra.addFlashAttribute("success", "Producto eliminado");
  // return "redirect:/admin/productos";
  // }
}