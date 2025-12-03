package com.example.botica.model.tienda;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDate fecha;
  private String estado;
  private String metodoPago;
  private Double total;
  private String recojo;
  private String horarioRecojo;
  private String nombreRecojo;
  private String observaciones;
  private boolean delivery;

  @Column(name = "usuario_id")
  private Integer usuarioId;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<DetallePedido> detalles;

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

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(String metodoPago) {
    this.metodoPago = metodoPago;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getRecojo() {
    return recojo;
  }

  public void setRecojo(String recojo) {
    this.recojo = recojo;
  }

  public String getHorarioRecojo() {
    return horarioRecojo;
  }

  public void setHorarioRecojo(String horarioRecojo) {
    this.horarioRecojo = horarioRecojo;
  }

  public String getNombreRecojo() {
    return nombreRecojo;
  }

  public void setNombreRecojo(String nombreRecojo) {
    this.nombreRecojo = nombreRecojo;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public Integer getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }

  public List<DetallePedido> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetallePedido> detalles) {
    this.detalles = detalles;
  }

  public boolean isDelivery() {
    return delivery;
  }

  public void setDelivery(boolean delivery) {
    this.delivery = delivery;
  }
}
