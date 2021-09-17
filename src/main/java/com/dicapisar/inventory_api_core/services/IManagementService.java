package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsResponseDTO;

import java.util.List;

public interface IManagementService {
    List<TypeRecordsResponseDTO> getListTypeRecordsDTO();
}
