package com.will.crud.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="futbolistas")
public class Futbolista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de la persona
    private String nombres; // Nombres de la persona
    private String apellidos; // Apellidos de la persona
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento; // Fecha de nacimiento de la persona
    private String email; // Correo electrónico de la persona
    private String telefono; // Número de teléfono de la persona
    private String posicion; // Posición en la que juega el futbolista
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @PrePersist
    public void antesDePersistir(){
        this.fechaCreacion=LocalDate.now();
    }

    @PreUpdate
    public void antesDeUpdate(){
        this.fechaModificacion=LocalDate.now();
    }

    // Getters y setters generados automáticamente por Lombok

    // Constructor sin argumentos generado automáticamente por Lombok

    // Constructor con todos los argumentos generado automáticamente por Lombok
}
