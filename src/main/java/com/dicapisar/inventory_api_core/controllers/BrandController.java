package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
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
    public ResponseEntity<?> createNewBrand(@RequestBody @Valid BrandRequestDTO brandRequestDTO)
            throws BrandAlredyExistsException {
        brandService.createNewBrand(brandRequestDTO, 1L);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Long id)
            throws BrandNotFoundException {
        return new ResponseEntity<>(brandService.getBrandResponseDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrandById(@PathVariable Long id, @RequestBody BrandRequestDTO brandRequestDTO)
            throws BrandNotFoundException {
        return new ResponseEntity<>(brandService.updateBrandById(id, brandRequestDTO, 1L), HttpStatus.OK);
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateBrandById(@PathVariable Long id) throws BrandNotFoundException {
        brandService.deactivateBrandById(id, 1L);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
