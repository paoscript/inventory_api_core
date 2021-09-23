package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.models.Provider;

public class ProviderUtil {
    public static ProviderResponseDTO toProviderResponseDTO(Provider provider) {
        ProviderResponseDTO providerResponseDTO = new ProviderResponseDTO();

        providerResponseDTO.setName(provider.getName());
        providerResponseDTO.setDocumentNumber(provider.getDocumentNumber());
        providerResponseDTO.setPhoneNumber(provider.getPhoneNumber());
        providerResponseDTO.setEmail(provider.getEmail());
        providerResponseDTO.setAddress(provider.getAddress());

        return providerResponseDTO;
    }
}
