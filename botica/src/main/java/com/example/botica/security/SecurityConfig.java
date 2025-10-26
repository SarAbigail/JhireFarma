package com.example.botica.security;

import com.example.botica.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final UsuarioService usuarioService;

  public SecurityConfig(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/css/**", "/img/**", "/js/**", "/login", "/categoria/**").permitAll()
            .requestMatchers("/administracion/**").hasRole("ADMIN")
            .requestMatchers("/ventas/**").hasRole("VENDEDOR")
            .anyRequest().authenticated())
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/redirectRol", true)
            .permitAll())
        .logout(logout -> logout.permitAll());

    return http.build();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(usuarioService);
    // authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    return authProvider;
  }
}
