
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