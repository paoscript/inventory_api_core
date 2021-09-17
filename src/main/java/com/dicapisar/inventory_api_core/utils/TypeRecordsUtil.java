package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsResponseDTO;
import com.dicapisar.inventory_api_core.models.TypeRecords;

import java.util.ArrayList;
import java.util.List;

public class TypeRecordsUtil {
    public static List<TypeRecordsResponseDTO> toListTypeRecordsDTO(List<TypeRecords> typeRecordsList) {
        List<TypeRecordsResponseDTO> typeRecordsDTOList = new ArrayList<>();

        for (TypeRecords t: typeRecordsList) {
            TypeRecordsResponseDTO typeRecordsDTO = new TypeRecordsResponseDTO(t.getEnglishName(), t.getSpanishName(), t.getUrl());
            typeRecordsDTOList.add(typeRecordsDTO);
        }

        return typeRecordsDTOList;

    }
}
