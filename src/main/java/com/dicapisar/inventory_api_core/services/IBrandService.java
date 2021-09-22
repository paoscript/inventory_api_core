package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.Exeptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;

import java.util.List;

public interface IBrandService {
    List<BrandResponseDTO> getListBrand(boolean isActive) throws ListNotFoundException;
    void createNewBrand(BrandRequestDTO brandCreateRequestDTO, Long idUser) throws ExistingRegistrationException;
    BrandResponseDTO getBrandResponseDTO(Long idBrand) throws BrandNotFoundException;
    BrandResponseDTO updateBrandById(Long idBrand, BrandRequestDTO brandRequestDTO, Long idUser) throws BrandNotFoundException;
    void changeStatusActiveById(Long idBrand, Long idUser, Boolean status) throws BrandNotFoundException;
}
