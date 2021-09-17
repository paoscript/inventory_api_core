package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandCreateRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.repositories.IBrandRepository;
import com.dicapisar.inventory_api_core.utils.Brandutil;
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

    public BrandResponseDTO getBrandResponseDTO(Long idBrand) throws BrandNotFoundException {
        Brand brand = brandRepository.findBrandById(idBrand);
        if(brand == null) {
            throw new BrandNotFoundException(idBrand);
        }
        return Brandutil.toBrandResonseDTO(brand);
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
