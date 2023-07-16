package com.will.crud.repository;

import com.will.crud.model.entity.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories // Habilita los repositorios de JPA
@Repository // Marca esta interfaz como un componente de repositorio de Spring
public interface FutbolistaRepository extends JpaRepository<Futbolista, Long> {
    // Esta interfaz extiende JpaRepository para acceder y administrar entidades de tipo Persona en la base de datos
    // La clase Persona es el tipo de entidad que se gestionará y Long es el tipo de dato del identificador único de las entidades

    // No se necesitan métodos adicionales aquí, ya que JpaRepository proporciona automáticamente los métodos básicos
    // de CRUD (Create, Read, Update, Delete) para el tipo de entidad especificado. Estos métodos pueden ser utilizados
    // directamente o se pueden definir consultas personalizadas si es necesario.
}

