package com.example.botica.model;


  public class Venta {
    private int id;
    private String cliente;
    private double total;

    public Venta(int id, String cliente, double total) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
    }

    // Getters
    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public double getTotal() { return total; }
}
