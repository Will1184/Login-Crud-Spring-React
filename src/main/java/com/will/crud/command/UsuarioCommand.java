package com.will.crud.command;

import com.will.crud.model.enums.Rol;
import com.will.crud.model.request.RegisterRequest;
import com.will.crud.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCommand implements CommandLineRunner {
    private final AuthenticationService service;

    public UsuarioCommand(AuthenticationService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        service.register(RegisterRequest.builder()
                .firstname("Brandon")
                .lastname("Gomez")
                .email("example@example.com")
                .username("Araragi")
                .password("12345")
                .rol(Rol.ADMIN)
                .build());

        service.register(RegisterRequest.builder()
                .firstname("Brandon")
                .lastname("Gomez")
                .email("example1@example.com")
                .username("Lulu")
                .password("12345")
                .rol(Rol.MANAGER)
                .build());

        service.register(RegisterRequest.builder()
                .firstname("Brandon")
                .lastname("Gomez")
                .email("example2@example.com")
                .username("ML")
                .password("12345")
                .rol(Rol.USER)
                .build());
    }
}
