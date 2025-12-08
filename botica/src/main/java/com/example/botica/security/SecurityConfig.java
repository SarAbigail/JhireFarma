package com.example.botica.security;

import com.example.botica.service.UsuarioService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UsuarioService usuarioService;
  private final PasswordEncoder passwordEncoder;

  public SecurityConfig(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
    this.usuarioService = usuarioService;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(usuarioService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/css/**", "/img/**", "/js/**", "/login", "/registro",
                "/registro/**", "/pedido", "/confirmacion-invitado", "/confirmacion-invitado/**",
                "/categoria/**",
                "/api/**", "/carrito/**", "/producto/**", "/busqueda", "/busqueda/**")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/pedido/guardar")
            .permitAll()
            .requestMatchers("/administracion/**").hasRole("ADMIN")
            .requestMatchers("/ventas/**").hasRole("VENDEDOR")
            .anyRequest().authenticated())
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/redirectRol", true)
            .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .permitAll());

    return http.build();
  }
}
