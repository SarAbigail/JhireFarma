package com.example.botica.service;

import com.example.botica.model.tienda.Usuario;
import com.example.botica.repository.tienda.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

  @Autowired
  private final UsuarioRepository usuarioRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository,
      PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  // Por probar
  public Usuario save(Usuario usuario) {
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    return usuarioRepository.save(usuario);
  }

  public Usuario findByEmail(String email) {
    return usuarioRepository.findByEmail(email)
        .orElse(null);
  }

  // public void autoLogin(Usuario usuario) {
  // UsernamePasswordAuthenticationToken authToken = new
  // UsernamePasswordAuthenticationToken(
  // usuario.getEmail(),
  // usuario.getPassword(),
  // List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));

  // SecurityContextHolder.getContext().setAuthentication(authToken);
  // }

  // Todo estoy sí funciona, incluso lo comentado
  // public UsuarioService(UsuarioRepository usuarioRepository) {
  // this.usuarioRepository = usuarioRepository;
  // }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    return User.builder()
        .username(usuario.getEmail())
        .password(usuario.getPassword())
        .roles(usuario.getRol()) // "ROLE_"
        .build();
  }

  // Método auxiliar para crear usuarios
  public void crearUsuario(String email, String password, String rol) {
    Usuario u = new Usuario();
    u.setEmail(email);
    u.setPassword(new BCryptPasswordEncoder().encode(password));
    u.setRol(rol);
    usuarioRepository.save(u);
  }
}
