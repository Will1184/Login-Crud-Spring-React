package com.will.crud.service;

import com.will.crud.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;

  @Override
  public void logout(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication
  ) {
    // Obtiene el encabezado Authorization de la solicitud
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      // Si el encabezado es nulo o no comienza con "Bearer ", no se realiza ninguna acción
      return;
    }
    // Extrae el JWT de la cadena del encabezado
    jwt = authHeader.substring(7);
    // Busca el token en el repositorio de tokens
    var storedToken = tokenRepository.findByToken(jwt)
            .orElse(null);
    if (storedToken != null) {
      // Marca el token como expirado y revocado
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      // Limpia el contexto de seguridad
      SecurityContextHolder.clearContext();
    }
  }
}
