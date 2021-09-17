package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandCreateRequestDTO;

public interface IBrandService {
    void createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUser) throws BrandAlredyExistsException;
}
