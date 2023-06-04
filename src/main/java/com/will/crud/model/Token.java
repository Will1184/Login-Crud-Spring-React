package com.will.crud.model;

import com.will.crud.repository.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Identificador único del token

    @Column(unique = true)
    private String token; // Valor del token

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER; // Tipo de token

    private boolean revoked; // Indica si el token ha sido revocado

    private boolean expired; // Indica si el token ha expirado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Usuario usuario; // Usuario asociado al token

    // Getters y setters generados automáticamente por Lombok

    // Constructor sin argumentos generado automáticamente por Lombok

    // Constructor con todos los argumentos generado automáticamente por Lombok

}
