package com.dicapisar.inventory_api_core.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorUserWithoutPermissions extends InventoryAPICoreException{
    public ErrorUserWithoutPermissions(){
        super("User without permissions to the action", HttpStatus.FORBIDDEN);
    }
}
