package com.will.crud.controller;

import com.will.crud.model.entity.Usuario;
import com.will.crud.model.request.ChangeEmailRequest;
import com.will.crud.model.request.ChangePasswordRequest;
import com.will.crud.model.request.ChangeRolRequest;
import com.will.crud.model.request.ChangeUsernameRequest;
import com.will.crud.model.response.*;
import com.will.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UsuarioResponse>>findAllUsuarios(){
        return ResponseEntity.ok(usuarioService.findAllUsuarios());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<Usuario>findUsuarioId(@RequestBody Integer id, @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.findUsuarioId(id,usuario));
    }

    @PostMapping("/changeEmail")
    public ResponseEntity<ChangeEmailResponse>changeEmail(
            @RequestBody ChangeEmailRequest request){
        return ResponseEntity.ok(usuarioService.changeEmail(request));
    }

    @PostMapping("/changeUsername")
    public ResponseEntity<ChangeUsernameResponse>changeUsername(
            @RequestBody ChangeUsernameRequest request){
        return ResponseEntity.ok(usuarioService.changeUsername(request));
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ChangePasswordResponse>changePassword(
            @RequestBody ChangePasswordRequest request){
        return ResponseEntity.ok(usuarioService.changePassword(request));
    }

    @PostMapping("/changeRol")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ChangeRolResponse>changeRole(
            @RequestBody ChangeRolRequest request){
        return ResponseEntity.ok(usuarioService.changeRol(request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.deleteUser(id));

    }

}
