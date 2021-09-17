package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsResponseDTO;
import com.dicapisar.inventory_api_core.repositories.ITypeRecordsRepository;
import com.dicapisar.inventory_api_core.utils.TypeRecordsUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManagementService implements IManagementService{

    private ITypeRecordsRepository typeRecordsRepository;

    public List<TypeRecordsResponseDTO> getListTypeRecordsDTO() {
        return TypeRecordsUtil.toListTypeRecordsDTO(typeRecordsRepository.getListTypeRecords());
    }
}
