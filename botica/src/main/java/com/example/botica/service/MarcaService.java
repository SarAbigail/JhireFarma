package com.example.botica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.botica.model.Marca;
import com.example.botica.model.Presentacion;
import com.example.botica.repository.MarcaRepository;

@Service
public class MarcaService {
  private final MarcaRepository repo;

  public MarcaService(MarcaRepository repo) {
    this.repo = repo;
  }

  public List<Marca> listar() {
    return repo.findAll();
  }

  public Marca guardar(Marca m) {
    return repo.save(m);
  }

  public void eliminar(Integer id) {
    repo.deleteById(id);
  }

  public Marca buscarPorId(Integer marcaId) {
    return repo.findById(marcaId).orElse(null);
  }
}
