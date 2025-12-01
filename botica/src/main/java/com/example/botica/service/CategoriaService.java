package com.example.botica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.botica.model.Categoria;
import com.example.botica.model.Producto;
import com.example.botica.repository.CategoriaRepository;

@Service
public class CategoriaService {
  @Autowired
  private final CategoriaRepository repo;

  public CategoriaService(CategoriaRepository repo) {
    this.repo = repo;
  }

  public List<Categoria> listar() {
    return repo.findAll();
  }

  public Categoria guardar(Categoria c) {
    return repo.save(c);
  }

  public void eliminar(Integer id) {
    repo.deleteById(id);
  }

  public Categoria buscarPorId(Integer categoriaId) {
    return repo.findById(categoriaId).orElse(null);
  }
}
