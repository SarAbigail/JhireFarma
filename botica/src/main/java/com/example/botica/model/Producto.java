package com.example.botica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nombre;
  private String presentacion;
  private Double precio;
  private String imagen;
  private Integer visitas;
  private String categoria;
  private String marca;

  // Constructor vacío para JPA
  public Producto() {
  }

  // Constructor opcional
  public Producto(String nombre, String presentacion, Double precio, String imagen, Integer visitas) {
    this.nombre = nombre;
    this.presentacion = presentacion;
    this.precio = precio;
    this.imagen = imagen;
    this.visitas = visitas;
  }

  // Getters y Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPresentacion() {
    return presentacion;
  }

  public void setPresentacion(String presentacion) {
    this.presentacion = presentacion;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public Integer getVisitas() {
    return visitas;
  }

  public void setVisitas(Integer visitas) {
    this.visitas = visitas;
  }
}
