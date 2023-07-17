package com.will.crud.model.request;

import com.will.crud.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRolRequest {
    private String username;
    private Rol role;
}
