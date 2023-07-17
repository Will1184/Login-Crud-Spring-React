package com.will.crud.controller;

import com.will.crud.model.request.AuthenticationRequest;
import com.will.crud.model.response.AuthenticationResponse;
import com.will.crud.model.request.RegisterRequest;
import com.will.crud.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja las solicitudes relacionadas con la autenticación y registro de usuarios.
 * Las rutas base para todas las solicitudes en este controlador comienzan con "/api/v1/auth".
 */
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthenticationController{

  @Autowired
  private AuthenticationService service;
  /**
   * Maneja la solicitud de registro de un nuevo usuario.
   *
   * @param request objeto de solicitud de registro
   * @return ResponseEntity con la respuesta de autenticación
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody RegisterRequest request
  ){
      return ResponseEntity.ok(service.register(request));
  }

  /**
   * Maneja la solicitud de autenticación de un usuario.
   *
   * @param request objeto de solicitud de autenticación
   * @return ResponseEntity con la respuesta de autenticación
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
