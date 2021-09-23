package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.models.Provider;
import com.dicapisar.inventory_api_core.repositories.IProviderRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ProviderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProviderService implements IProviderService {

    private IProviderRepository providerRepository;
    private IUserRepository userRepository;

    public List<ProviderResponseDTO> getListProvider(boolean isActive) throws ListNotFoundException {
        List<Provider> providerList = providerRepository.getListProvider(isActive);

        if (providerList.isEmpty()) {
            throw new ListNotFoundException("Provider");
        }

        List<ProviderResponseDTO> providerResponseDTOList = new ArrayList<>();

        for (Provider provider : providerList) {
            providerResponseDTOList.add(ProviderUtil.toProviderResponseDTO(provider));
        }

        return providerResponseDTOList;
    }
}
