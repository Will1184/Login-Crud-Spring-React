package com.will.crud.command;

import com.will.crud.model.entity.Futbolista;
import com.will.crud.repository.FutbolistaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class FutbolistasCommand implements CommandLineRunner {

    private final FutbolistaRepository repository;

    public FutbolistasCommand(FutbolistaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        createFutbolistas(repository);
        Pageable pageable = PageRequest.of(0,5);
        Page<Futbolista> futbolistas = repository.findAll(pageable);
        futbolistas.forEach(System.out::println);

    }
    public static void createFutbolistas(FutbolistaRepository repository){

        List<Futbolista> futbolistas = Arrays.asList(
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build(),
                Futbolista.builder()
                        .nombres("Brandon William")
                        .apellidos("Gomez")
                        .fechaNacimiento(LocalDate.parse("2003-01-17"))
                        .email("prueba@gmail.com")
                        .telefono("121212121")
                        .posicion("delantero")
                        .build()
        );
        repository.saveAll(futbolistas);
    }
}
