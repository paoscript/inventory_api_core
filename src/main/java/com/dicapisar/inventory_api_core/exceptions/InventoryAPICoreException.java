package com.dicapisar.inventory_api_core.exceptions;

import com.dicapisar.inventory_api_core.dtos.ErrorDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InventoryAPICoreException extends Exception{
    private final ErrorDTO error;
    private final HttpStatus status;

    public InventoryAPICoreException(String message, HttpStatus status) {
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setErrorName(this.getClass().getSimpleName());
        this.status = status;
    }

}
