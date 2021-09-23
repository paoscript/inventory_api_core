package com.dicapisar.inventory_api_core.exceptions;

import org.springframework.http.HttpStatus;

public class ExistingRegistrationException extends InventoryAPICoreException{
    public ExistingRegistrationException(String typeRegister, String typeAttribute, String nameAttribute) {
        super("The '" + typeAttribute + "' attribute of the '" + typeRegister + "' record type identified with '" + nameAttribute + "' already exists", HttpStatus.BAD_REQUEST);
    }
}
