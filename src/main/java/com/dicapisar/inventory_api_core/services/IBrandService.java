package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;

public interface IBrandService {
    void createNewBrand(BrandRequestDTO brandCreateRequestDTO, Long idUser) throws BrandAlredyExistsException;
    BrandResponseDTO getBrandResponseDTO(Long idBrand) throws BrandNotFoundException;
    BrandResponseDTO updateBrandById(Long idBrand, BrandRequestDTO brandRequestDTO, Long idUser) throws BrandNotFoundException;
    void deactivateBrandById(Long idBrand, Long idUser) throws BrandNotFoundException;
}
