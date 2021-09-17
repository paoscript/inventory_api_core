package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandCreateRequestDTO;
import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.repositories.IBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements IBrandService{

    private IBrandRepository brandRepository;

    public void createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUser) throws BrandAlredyExistsException {

        List<Brand> brandList = brandRepository.getBrandsByName(brandCreateRequestDTO.getName());

        if (!brandList.isEmpty()) {
            if (isRegistrationAlreadyExists(brandList, brandCreateRequestDTO.getName())) {
                throw new BrandAlredyExistsException(brandCreateRequestDTO.getName());
            }
        } else {
            brandRepository.insertBrand(brandCreateRequestDTO.getName(), idUser);
        }

    }

    private boolean isRegistrationAlreadyExists(List<Brand> brandList, String name) {
        for (Brand brand : brandList) {
            if (brand.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


}
