package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;

import java.util.List;

public interface IBrandService {
    List<BrandResponseDTO> getListBrand(boolean isActive) throws ListNotFoundException;
    BrandResponseDTO createNewBrand(BrandRequestDTO brandCreateRequestDTO, Long idUser) throws ExistingRegistrationException;
    BrandResponseDTO getBrandResponseDTO(Long idBrand) throws RegisterNotFoundException;
    BrandResponseDTO updateBrandById(Long idBrand, BrandRequestDTO brandRequestDTO, Long idUser) throws RegisterNotFoundException;
    void changeStatusActiveById(Long idBrand, Long idUser, Boolean status) throws RegisterNotFoundException;
}
