package com.example.botica.controller.vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.botica.model.Venta;
import com.example.botica.service.vendedor.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaRestController {

  @Autowired
  private VentaService ventaService;

  @PostMapping
  public ResponseEntity<String> registrar(@RequestBody Venta venta) {
    ventaService.registrarVenta(venta);
    return ResponseEntity.ok().body("ok");

  }
}