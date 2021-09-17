package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandCreateRequestDTO;
import com.dicapisar.inventory_api_core.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private IBrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewBrand(@RequestBody @Valid BrandCreateRequestDTO brandCreateRequestDTO)
            throws BrandAlredyExistsException {
        brandService.createNewBrand(brandCreateRequestDTO, 1L);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
