// package com.example.botica.model;

// public class Venta {
// private int id;
// private String cliente;
// private double total;

// public Venta(int id, String cliente, double total) {
// this.id = id;
// this.cliente = cliente;
// this.total = total;
// }

// // Getters
// public int getId() {
// return id;
// }

// public String getCliente() {
// return cliente;
// }

// public double getTotal() {
// return total;
// }
// }
package com.example.botica.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "venta")
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDate fecha;
  private Double total;
  private String cliente;
  private String metodoPago;
  private String serie;
  private String numero;

  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
  private List<VentaDetalle> detalles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public String getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(String metodoPago) {
    this.metodoPago = metodoPago;
  }

  public List<VentaDetalle> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<VentaDetalle> detalles) {
    this.detalles = detalles;
  }

  public String getSerie() {
    return serie;
  }

  public void setSerie(String serie) {
    this.serie = serie;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  // Constructor vacío para guardar ventas
  public Venta() {
  }
}
