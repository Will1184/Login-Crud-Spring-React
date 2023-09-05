package com.will.crud.controller;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class GenericController {

    protected Map<String,Object> obtenerValidaciones(BindingResult result){
        Map<String,Object> validaciones = new HashMap<>();
        result.getFieldErrors()
                .forEach(error-> validaciones.put(error.getField(),error.getDefaultMessage()));
        return validaciones;
    }

}
