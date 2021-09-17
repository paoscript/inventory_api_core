package com.dicapisar.inventory_api_core.Exeptions;

import org.springframework.http.HttpStatus;

public class ListNotFoundException extends InventoryAPICoreException{
    public ListNotFoundException(String typeRecord) {
        super( typeRecord + " list Not Found", HttpStatus.NOT_FOUND);
    }
}
