package com.example.botica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.botica.model.Categoria;
import com.example.botica.model.Presentacion;
import com.example.botica.repository.PresentacionRepository;

@Service
public class PresentacionService {

  private final PresentacionRepository repo;

  public PresentacionService(PresentacionRepository repo) {
    this.repo = repo;
  }

  public List<Presentacion> listar() {
    return repo.findAll();
  }

  public Presentacion guardar(Presentacion p) {
    return repo.save(p);
  }

  public void eliminar(Integer id) {
    repo.deleteById(id);
  }

  public Presentacion buscarPorId(Integer presentacionId) {
    return repo.findById(presentacionId).orElse(null);
  }
}
