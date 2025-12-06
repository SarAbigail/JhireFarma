package com.example.botica.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
  private Integer stock;
  @Column(name = "fecha_vencimiento")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaVencimiento;
  private Boolean visible;
  @Column(name = "receta_medica")
  private Boolean recetaMedica;
  private String descripcion;
  @ManyToMany
  @JoinTable(name = "producto_sintoma", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "sintoma_id"))
  private List<Sintoma> sintomas;
  @ManyToOne
  @JoinColumn(name = "marca_id")
  private Marca marcaId;
  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoriaId;
  @ManyToOne
  @JoinColumn(name = "presentacion_id")
  private Presentacion presentacionId;
  @Transient
  private int cantidad = 1;

  // Constructor vacío para JPA
  public Producto() {
  }

  // Constructor opcional
  public Producto(String nombre, String presentacion, Double precio, String imagen, Integer visitas, String categoria,
      String marca) {
    this.nombre = nombre;
    this.presentacion = presentacion;
    this.precio = precio;
    this.imagen = imagen;
    this.visitas = visitas;
    this.categoria = categoria;
    this.marca = marca;
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

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int i) {
    this.cantidad = i;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public List<Sintoma> getSintomas() {
    return sintomas;
  }

  public void setSintomas(List<Sintoma> sintomas) {
    this.sintomas = sintomas;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Boolean getRecetaMedica() {
    return recetaMedica;
  }

  public void setRecetaMedica(Boolean recetaMedica) {
    this.recetaMedica = recetaMedica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Marca getMarcaId() {
    return marcaId;
  }

  public void setMarcaId(Marca marca_) {
    this.marcaId = marca_;
  }

  public Categoria getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Categoria categoria_) {
    this.categoriaId = categoria_;
  }

  public Presentacion getPresentacionId() {
    return presentacionId;
  }

  public void setPresentacionId(Presentacion presentacion_) {
    this.presentacionId = presentacion_;
  }

}
