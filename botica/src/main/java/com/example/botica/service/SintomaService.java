package com.example.botica.service;

import com.example.botica.model.Categoria;
import com.example.botica.model.Sintoma;
import com.example.botica.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SintomaService {

  private final SintomaRepository repo;

  @Autowired
  public SintomaService(SintomaRepository repo) {
    this.repo = repo;
  }

  public List<Sintoma> listar() {
    return repo.findAll();
  }

  public Sintoma guardar(Sintoma s) {
    return repo.save(s);
  }

  public void eliminar(Integer id) {
    repo.deleteById(id);
  }

  public Sintoma buscarPorId(Integer sintomaId) {
    return repo.findById(sintomaId).orElse(null);
  }
}
