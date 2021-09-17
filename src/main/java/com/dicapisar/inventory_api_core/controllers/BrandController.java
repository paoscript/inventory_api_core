package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandCreateRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Long id)
            throws BrandNotFoundException {
        return new ResponseEntity<>(brandService.getBrandResponseDTO(id), HttpStatus.OK);
    }
}
