package com.dicapisar.inventory_api_core.Exeptions;

import org.springframework.http.HttpStatus;

public class BrandAlredyExistsException extends InventoryAPICoreException{
    public BrandAlredyExistsException(String nameBrand) {
        super("The brand '" + nameBrand + "' already exists", HttpStatus.BAD_REQUEST);
    }
}
