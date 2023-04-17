package com.will.crud.controller;

import com.will.crud.auth.*;
import com.will.crud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/changePassword")
  public ResponseEntity<ChangePasswordResponse> changePassword(
          @RequestBody ChangePasswordRequest request
  ) {
    return ResponseEntity.ok(service.changePassword(request));
  }

  @PostMapping("/changeEmail")
  public ResponseEntity<ChangeEmailResponse> changeEmail(
          @RequestBody ChangeEmailRequest request
  ) {
    return ResponseEntity.ok(service.changeEmail(request));
  }
  @PostMapping("/userlogout")
  public ResponseEntity<LogoutResponse> logoutResponse(
          @RequestBody LogoutRequest request
  ) {
    return ResponseEntity.ok(service.logoutResponse(request));
  }
}
