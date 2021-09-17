package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.models.Brand;

public class Brandutil {
    public static BrandResponseDTO toBrandResonseDTO(Brand brand) {
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO();

        brandResponseDTO.setId(brand.getId());
        brandResponseDTO.setName(brand.getName());

        return brandResponseDTO;
    }
}
