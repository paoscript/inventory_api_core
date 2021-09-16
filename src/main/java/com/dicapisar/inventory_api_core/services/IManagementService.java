package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IManagementService {
    List<TypeRecordsDTO> getListTypeRecordsDTO();
}
