package com.will.crud.repository;

import com.will.crud.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar un usuario por su nombre de usuario
    Optional<Usuario> findByUsername(String username);
}
