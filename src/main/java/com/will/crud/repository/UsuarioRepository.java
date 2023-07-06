package com.will.crud.repository;

import com.will.crud.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar un usuario por su nombre de usuario
    Optional<Usuario> findByUsername(String username);
}
