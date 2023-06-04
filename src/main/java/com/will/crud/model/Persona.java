package com.will.crud.model;

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
@Table(name="futbolistas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de la persona

    private String nombres; // Nombres de la persona
    private String apellidos; // Apellidos de la persona
    private String fecha_nacimiento; // Fecha de nacimiento de la persona
    private String email; // Correo electrónico de la persona
    private String telefono; // Número de teléfono de la persona
    private String posicion; // Posición en la que juega el futbolista

    // Getters y setters generados automáticamente por Lombok

    // Constructor sin argumentos generado automáticamente por Lombok

    // Constructor con todos los argumentos generado automáticamente por Lombok
}
