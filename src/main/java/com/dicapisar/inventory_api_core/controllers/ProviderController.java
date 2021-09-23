package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.services.IProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/provider")
public class ProviderController {

    private IProviderService providerService;

    @GetMapping("/list")
    public ResponseEntity<List<ProviderResponseDTO>> getListProvider(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) throws ListNotFoundException {
        return new ResponseEntity<>(providerService.getListProvider(isActive), HttpStatus.OK);
    }
}
