package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;

import java.util.List;

public interface IProviderService {
    List<ProviderResponseDTO> getListProvider(boolean isActive) throws ListNotFoundException;
}
