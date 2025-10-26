package com.example.botica.model;

public class Producto {
  private String id;
  private String nombre;
  private String presentacion;
  private double precio;

  public Producto(String id, String nombre, String presentacion, double precio) {
    this.id = id;
    this.nombre = nombre;
    this.presentacion = presentacion;
    this.precio = precio;
  }
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPresentacion() { return presentacion; }
    public double getPrecio() {
      return precio;
    }
    public String toSearchableString() {
        return (id != null ? id.toString() : "") + " "
             + (nombre != null ? nombre : "") + " "
             + (presentacion != null ? presentacion : "");
    }
}
