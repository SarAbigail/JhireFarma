package com.example.botica.model.tienda;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CarritoItem> items = new ArrayList<>();
  // getters/setters

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<CarritoItem> getItems() {
    return items;
  }

  public void setItems(List<CarritoItem> items) {
    this.items = items;
  }

}
