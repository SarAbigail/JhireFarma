package com.example.botica.repository;
import com.example.botica.model.Producto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {
    private List<Producto> productos = new ArrayList<>();
    public ProductoRepository() {
        productos.add(new Producto("1", "Paracetamol", "Tabletas 500mg", 12.5));
        productos.add(new Producto("2", "Ibuprofeno", "Cápsulas 400mg", 15.0));
        productos.add(new Producto("3", "Jarabe para la tos", "Frasco 120ml", 18.0));
        productos.add(new Producto("4", "Amoxicilina", "Suspensión 250mg/5ml", 25.0));
    }
    public List<Producto> listar() {
      return productos;
    }
    public List<Producto> buscar(String texto) {
        String query = texto.toLowerCase();
        return productos.stream()
                .filter(p -> p.toSearchableString().toLowerCase().contains(query))
                .toList();
    }
}