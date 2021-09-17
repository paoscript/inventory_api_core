package com.dicapisar.inventory_api_core.Exeptions;

import org.springframework.http.HttpStatus;

public class BrandNotFoundException extends InventoryAPICoreException{
    public BrandNotFoundException(Long idBrand) {
        super("The brand whit id '" + idBrand + "' Not Found", HttpStatus.NOT_FOUND);
    }
}
