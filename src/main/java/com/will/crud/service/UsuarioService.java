package com.will.crud.service;

import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.entity.Usuario;
import com.will.crud.model.enums.Rol;
import com.will.crud.model.request.ChangeEmailRequest;
import com.will.crud.model.request.ChangePasswordRequest;
import com.will.crud.model.request.ChangeRolRequest;
import com.will.crud.model.request.ChangeUsernameRequest;
import com.will.crud.model.response.*;
import com.will.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationService authenticationService;

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Old password does not match.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);

        authenticationService.revokeAllUserTokens(user);
        return new ChangePasswordResponse("Password updated successfully.");
    }
    public List<UsuarioResponse> findAllUsuarios(){
        List<Usuario>usuariosList= repository.findAll();
        List<UsuarioResponse> usuarioResponsesList=new ArrayList<>();
        usuariosList.forEach(usuario ->{
            UsuarioResponse usuarioResponse= UsuarioResponse.builder()
                    .id(usuario.getId())
                    .firstname(usuario.getFirstname())
                    .lastname(usuario.getLastname())
                    .email(usuario.getEmail())
                    .build();
            usuarioResponsesList.add(usuarioResponse);
        });
        return usuarioResponsesList;
    }
    public Usuario findUsuarioId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Futbolista does not exist with id: " + id));
    }
    public Usuario findUsuarioId(Integer id,Usuario usuarioDetails){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Futbolista does not exist with id: " + id));
        usuario.setFirstname(usuarioDetails.getFirstname());
        usuario.setLastname(usuarioDetails.getLastname());
        usuario.setEmail(usuarioDetails.getEmail());
        return repository.save(usuario);
    }
    public ChangeRolResponse changeRol(ChangeRolRequest request){
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));

        if (request.getRole()==null){
            request.setRole(Rol.USER);
        }
        user.setRole(request.getRole());
        repository.save(user);

        authenticationService.revokeAllUserTokens(user);
        return new ChangeRolResponse("Role updated successfully.");
    }
    public ChangeUsernameResponse changeUsername(ChangeUsernameRequest request){
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + request.getUsername()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Password does not match.");
        }

        user.setUsername(request.getNewUsername());
        repository.save(user);
        authenticationService.revokeAllUserTokens(user);
        return new ChangeUsernameResponse("Username Update Successfully");
    }
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request){
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + request.getUsername()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Password does not match.");
        }

        user.setEmail(request.getNewEmail());
        repository.save(user);
        authenticationService.revokeAllUserTokens(user);
        return new ChangeEmailResponse("Username Update Successfully");
    }
    public DeleteUserResponse deleteUser(Integer id){
        Usuario usuario=findUsuarioId(id);
        repository.delete(usuario);
        return DeleteUserResponse.builder()
                .message("El usuario Fue Eliminado correctamente")
                .build();
    }
}
