
//Segundo avance
// package com.example.botica.controller;
// import com.example.botica.model.Producto;
// import com.example.botica.repository.ProductoRepository;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/productos")
// public class ProductoRestController {
//     private final ProductoRepository repo;
//     public ProductoRestController(ProductoRepository repo) {
//         this.repo = repo;
//     }
//     @GetMapping
//     public List<Producto> listar(@RequestParam(required = false) String q) {
//         if (q != null && !q.isEmpty()) {
//             return repo.buscar(q);
//         }
//         return repo.listar();
//     }
// }

// package com.example.botica.controller;

// import com.example.botica.model.Producto;
// import com.example.botica.repository.ProductoRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/productos")
// public class ProductoRestController {

//   @Autowired
//   private ProductoRepository productoRepository;

//   @GetMapping
//   public List<Producto> listarProductos(@RequestParam(name = "buscar", required = false) String buscar) {
//     if (buscar != null && !buscar.isEmpty()) {
//       return productoRepository.findByNombreContainingIgnoreCase(buscar);
//     }
//     return productoRepository.findAll();
//   }
// }

package com.example.botica.controller;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

  @Autowired
  private ProductoRepository productoRepository;

  // Devuelve todos los productos o filtrados
  @GetMapping
  public List<Producto> listarProductos(@RequestParam(name = "buscar", required = false) String buscar) {
    if (buscar != null && !buscar.isEmpty()) {
      return productoRepository.buscarPorVariosCampos(buscar);
    }
    return productoRepository.findAll();
  }
}
