package com.example.botica.service.tienda;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.botica.model.tienda.Pedido;
import com.example.botica.model.tienda.Usuario;
import com.example.botica.repository.tienda.PedidoRepository;
import com.example.botica.repository.tienda.UsuarioRepository;

@Service
public class ClienteService {

  @Autowired
  private UsuarioRepository usuarioRepo;

  @Autowired
  private PedidoRepository pedidoRepo;

  public Usuario getUsuarioPorEmail(String email) {
    return usuarioRepo.findByEmail(email).orElseThrow();
  }

  public List<Pedido> getPedidosUsuario(String email) {
    return pedidoRepo.findByUsuarioEmail(email);
  }

  public Usuario actualizarDatosUsuario(Usuario usuarioActualizado) {
    Usuario u = usuarioRepo.findById(usuarioActualizado.getId()).orElseThrow();
    u.setNombre(usuarioActualizado.getNombre());
    u.setDireccion(usuarioActualizado.getDireccion());
    u.setTelefono(usuarioActualizado.getTelefono());
    // email y password opcional actualizar por separado
    return usuarioRepo.save(u);
  }
}
