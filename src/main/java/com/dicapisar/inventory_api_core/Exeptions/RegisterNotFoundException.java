package com.dicapisar.inventory_api_core.Exeptions;

import org.springframework.http.HttpStatus;

public class RegisterNotFoundException extends InventoryAPICoreException{
    public RegisterNotFoundException(String typeRegister, Long idBrand) {
        super("The type register '" + typeRegister +"' whit id: '" + idBrand + "' Not Found", HttpStatus.NOT_FOUND);
    }
}
