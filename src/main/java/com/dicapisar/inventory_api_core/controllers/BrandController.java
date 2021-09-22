package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.Exeptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.Exeptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.Exeptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private IBrandService brandService;

    @GetMapping("/list")
    public ResponseEntity<List<BrandResponseDTO>> getListBrand(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) throws ListNotFoundException {
        return new ResponseEntity<>(brandService.getListBrand(isActive), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewBrand(@RequestBody @Valid BrandRequestDTO brandRequestDTO)
            throws ExistingRegistrationException {
        brandService.createNewBrand(brandRequestDTO, 1L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Long id)
            throws RegisterNotFoundException {
        return new ResponseEntity<>(brandService.getBrandResponseDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrandById(@PathVariable Long id, @RequestBody BrandRequestDTO brandRequestDTO)
            throws RegisterNotFoundException {
        return new ResponseEntity<>(brandService.updateBrandById(id, brandRequestDTO, 1L), HttpStatus.OK);
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateBrandById(@PathVariable Long id) throws RegisterNotFoundException {
        brandService.changeStatusActiveById(id, 1L, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("activate/{id}")
    public ResponseEntity<?> activateBrandById(@PathVariable Long id) throws RegisterNotFoundException {
        brandService.changeStatusActiveById(id, 1L, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
