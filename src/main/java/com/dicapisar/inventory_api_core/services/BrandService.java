package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.BrandAlredyExistsException;
import com.dicapisar.inventory_api_core.Exeptions.BrandNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.BrandRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.BrandResponseDTO;
import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.models.User;
import com.dicapisar.inventory_api_core.repositories.IBrandRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.BrandUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements IBrandService{

    private IBrandRepository brandRepository;
    private IUserRepository userRepository;

    public void createNewBrand(BrandRequestDTO brandCreateRequestDTO, Long idUser) throws BrandAlredyExistsException {

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
        return BrandUtil.toBrandResponseDTO(brand);
    }

    public BrandResponseDTO updateBrandById(Long idBrand, BrandRequestDTO brandRequestDTO, Long idUser) throws BrandNotFoundException {
        Brand brand = brandRepository.findBrandById(idBrand);
        if(brand == null) {
            throw new BrandNotFoundException(idBrand);
        }

        return BrandUtil.toBrandResponseDTO(updateBrand(brand, brandRequestDTO, idUser));

    }

    private boolean isRegistrationAlreadyExists(List<Brand> brandList, String name) {
        for (Brand brand : brandList) {
            if (brand.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private Brand updateBrand(Brand brand, BrandRequestDTO brandRequestDTO, Long idUser) {

        User user = userRepository.findUserById(idUser);

        Brand brandUpdated = brand;

        if(!brandUpdated.getName().equals(brandRequestDTO.getName())) {
            brandUpdated.setName(brandRequestDTO.getName());
            brandUpdated.setUpdater(user);
            brandUpdated.setUpdatedAt(LocalDateTime.now());
        }

        brandRepository.save(brandUpdated);

        return brandUpdated;
    }

}
