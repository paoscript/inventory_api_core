package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.requests.ProviderRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;

import java.util.List;

public interface IProviderService {
    List<ProviderResponseDTO> getListProvider(boolean isActive) throws ListNotFoundException;
    void createNewProvider(ProviderRequestDTO providerRequestDTO, Long idUser) throws ExistingRegistrationException;
    ProviderResponseDTO getProviderResponseDTO(Long idProvider) throws RegisterNotFoundException;
    ProviderResponseDTO updateProviderById(Long idProvider, ProviderRequestDTO providerRequestDTO, Long idUser) throws RegisterNotFoundException;
}
