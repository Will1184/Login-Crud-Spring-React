package com.will.crud.service;


import com.will.crud.auth.*;
import com.will.crud.model.Rol;
import com.will.crud.model.Usuario;
import com.will.crud.repository.TokenRepository;
import com.will.crud.repository.UsuarioRepository;
import com.will.crud.security.JwtUtils;
import com.will.crud.token.Token;
import com.will.crud.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UsuarioRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = Usuario.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
            .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Rol.USER)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }


  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = repository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
            .username(user.getUsername())
            .email(user.getEmail())
            .role(user.getRole().name())
            .build();
  }

  private void saveUserToken(Usuario user, String jwtToken) {
    var token = Token.builder()
        .usuario(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Usuario user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
    var user = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

    if (!passwordEncoder.matches(request.getOldpassword(), user.getPassword())) {
      throw new BadCredentialsException("Old password does not match.");
    }

    user.setPassword(passwordEncoder.encode(request.getNewpassword()));
    repository.save(user);

    // Revoke all user tokens to force re-authentication with the new password
    revokeAllUserTokens(user);

    return new ChangePasswordResponse("Password updated successfully.");
  }
  public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
    var user = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Password does not match.");
    }
    user.setEmail(request.getNewemail());
    repository.save(user);

    // Revoke all user tokens to force re-authentication with the new email
    revokeAllUserTokens(user);

    return new ChangeEmailResponse("Email updated successfully.");
  }
  public LogoutResponse logoutResponse (LogoutRequest request) {
    var user = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

    // Revoke all user tokens to force re-authentication with the new email
    revokeAllUserTokens(user);

    return new LogoutResponse("Logout successfully.");
  }
}
