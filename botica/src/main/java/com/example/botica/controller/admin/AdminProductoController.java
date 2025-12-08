package com.example.botica.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.botica.model.Categoria;
import com.example.botica.model.Marca;
import com.example.botica.model.Presentacion;
import com.example.botica.model.Producto;
import com.example.botica.repository.CategoriaRepository;
import com.example.botica.repository.MarcaRepository;
import com.example.botica.repository.PresentacionRepository;
import com.example.botica.repository.ProductoRepository;
import com.example.botica.service.ProductoService;
import com.example.botica.service.SintomaService;

@Controller
@RequestMapping("/admin")
public class AdminProductoController {

  @Autowired
  private ProductoService productoService;
  @Autowired
  private SintomaService sintomaService;
  @Autowired
  private MarcaRepository marcaRepository;
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private PresentacionRepository presentacionRepository;

  // Listar productos
  @GetMapping("/productos")
  public String listarProductos(Model model) {
    model.addAttribute("productos", productoService.obtenerTodosLosProductos());
    return "admin/listar_productos";
  }

  @GetMapping("/productos/ver/{id}")
  public String verProducto(@PathVariable Integer id, Model model) {
    Producto producto = productoService.buscarPorId(id);

    if (producto == null) {
      return "redirect:/admin/productos?error=ProductoNoEncontrado";
    }

    model.addAttribute("producto", producto);
    return "admin/ver_producto";
  }

  // Formulario nuevo producto
  @GetMapping("/productos/nuevo")
  public String nuevoProducto(Model model) {
    model.addAttribute("producto", new Producto());
    model.addAttribute("sintomas", sintomaService.listar());
    model.addAttribute("marcas", marcaRepository.findAll());
    model.addAttribute("categorias", categoriaRepository.findAll());
    model.addAttribute("presentaciones", presentacionRepository.findAll());

    return "admin/producto_form";
  }

  @PostMapping("/productos/guardar")
  public String guardarProducto(
      @ModelAttribute Producto producto,
      @RequestParam Integer presentacionId,
      @RequestParam Integer categoriaId,
      @RequestParam Integer marcaId) {

    Presentacion presentacion = presentacionRepository.findById(presentacionId).orElse(null);
    Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
    Marca marca = marcaRepository.findById(marcaId).orElse(null);

    producto.setPresentacionId(presentacion); // guarda la relación
    producto.setPresentacion(presentacion.getNombre()); // guarda el texto

    producto.setCategoriaId(categoria);
    producto.setCategoria(categoria.getNombre());

    producto.setMarcaId(marca);
    producto.setMarca(marca.getNombre());

    producto.setPresentacionId(presentacion);
    producto.setCategoriaId(categoria);
    producto.setMarcaId(marca);

    productoService.guardarProducto(producto);

    return "redirect:/admin/productos";
  }

  // Editar producto
  @GetMapping("/productos/editar/{id}")
  public String editarProducto(@PathVariable Integer id, Model model) {
    Producto producto = productoService.buscarPorId(id);
    if (producto == null) {
      // Si no existe, redirige al listado con mensaje de error
      return "redirect:/admin/productos?error=notfound";
    }
    model.addAttribute("producto", producto);
    model.addAttribute("sintomas", sintomaService.listar());
    model.addAttribute("marcas", marcaRepository.findAll());
    model.addAttribute("categorias", categoriaRepository.findAll());
    model.addAttribute("presentaciones", presentacionRepository.findAll());
    return "/admin/producto_form";
  }

  // Eliminar producto
  @PostMapping("/productos/eliminar/{id}")
  public String eliminarProducto(@PathVariable Integer id, RedirectAttributes ra) {
    productoService.eliminarProducto(id);
    ra.addFlashAttribute("success", "Producto eliminado");
    return "redirect:/admin/productos";
  }
}