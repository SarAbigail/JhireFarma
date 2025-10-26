package com.example.botica.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.botica.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
