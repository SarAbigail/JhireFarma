package com.example.botica.service;

import com.example.botica.model.Usuario;
import com.example.botica.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    return User.builder()
        .username(usuario.getUsername())
        .password(usuario.getPassword())
        .roles(usuario.getRol()) // "ROLE_"
        .build();
  }

  // Método auxiliar para crear usuarios
  public void crearUsuario(String username, String password, String rol) {
    Usuario u = new Usuario();
    u.setUsername(username);
    u.setPassword(new BCryptPasswordEncoder().encode(password));
    u.setRol(rol);
    usuarioRepository.save(u);
  }
}
