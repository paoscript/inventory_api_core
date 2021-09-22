package com.dicapisar.inventory_api_core.Exeptions;

import org.springframework.http.HttpStatus;

public class ExistingRegistrationException extends InventoryAPICoreException{
    public ExistingRegistrationException(String typeRegister, String nameRegister) {
        super("The type register " + typeRegister + " whit name: '" + nameRegister + "' already exists", HttpStatus.BAD_REQUEST);
    }
}
