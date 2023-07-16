package com.will.crud.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeración que define los permisos disponibles en el sistema.
 */
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),            // Permiso de lectura para el administrador
    ADMIN_UPDATE("admin:update"),        // Permiso de actualización para el administrador
    ADMIN_CREATE("admin:create"),        // Permiso de creación para el administrador
    ADMIN_DELETE("admin:delete"),        // Permiso de eliminación para el administrador
    MANAGER_READ("management:read"),     // Permiso de lectura para el gerente
    MANAGER_UPDATE("management:update"), // Permiso de actualización para el gerente
    MANAGER_CREATE("management:create"), // Permiso de creación para el gerente
    MANAGER_DELETE("management:delete")  // Permiso de eliminación para el gerente
    ;

    @Getter
    private final String permission;
}
