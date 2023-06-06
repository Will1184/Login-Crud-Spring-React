package com.will.crud.repository;

import com.will.crud.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    /**
     * Busca y devuelve todos los tokens válidos asociados a un usuario específico.
     *
     * @param id ID del usuario
     * @return Lista de tokens válidos asociados al usuario
     */
    @Query(value = """
      select t from Token t inner join Usuario u\s
      on t.usuario.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    /**
     * Busca un token por su valor.
     *
     * @param token Valor del token
     * @return Token encontrado, si existe
     */
    Optional<Token> findByToken(String token);
}
