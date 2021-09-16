package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.Exeptions.InventoryAPICoreException;
import com.dicapisar.inventory_api_core.dtos.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(InventoryAPICoreException.class)
    public ResponseEntity<ErrorDTO> handleGlobalException (InventoryAPICoreException e) {
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }
}
