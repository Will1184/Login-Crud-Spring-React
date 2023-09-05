package com.will.crud.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FutbolistaRequest {
    @NotEmpty
    private String nombres; // Nombres de la persona
    @NotEmpty
    private String apellidos; // Apellidos de la persona

    private LocalDate fechaNacimiento; // Fecha de nacimiento de la persona
    @NotEmpty
    private String email; // Correo electrónico de la persona
    @NotEmpty
    private String telefono; // Número de teléfono de la persona
    @NotEmpty
    private String posicion; // Posición en la que juega el futbolista
}
