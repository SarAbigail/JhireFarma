package com.example.botica.service;

import com.example.botica.model.Producto;
import com.example.botica.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> obtenerMasBuscados() {
        return repo.findTop5ByOrderByVisitasDesc();
    }
}
