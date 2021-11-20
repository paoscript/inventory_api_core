package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.models.User;

import java.time.LocalDateTime;

public class BrandUtil {
    public static BrandResponseDTO toBrandResponseDTO(Brand brand) {
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO();

        brandResponseDTO.setId(brand.getId());
        brandResponseDTO.setName(brand.getName());

        return brandResponseDTO;
    }

    public static Brand generateBrand(String name, User user) {
        return new Brand(null, name, true, LocalDateTime.now(), LocalDateTime.now(), user, user);
    }
}
