package com.will.crud.model;

import com.will.crud.repository.Rol;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @OneToMany(mappedBy = "usuario")
    private List<Token> tokens;

    // Implementación de métodos de UserDetails

    /**
     * Obtiene la lista de roles/granted authorities del usuario.
     * En este caso, se utiliza el método getAuthorityList() del rol.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorityList();
    }

    /**
     * Obtiene la contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Verifica si la cuenta del usuario no está expirada.
     * En este caso, se retorna siempre true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Verifica si la cuenta del usuario no está bloqueada.
     * En este caso, se retorna siempre true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica si las credenciales del usuario no están expiradas.
     * En este caso, se retorna siempre true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica si el usuario está habilitado.
     * En este caso, se retorna siempre true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
