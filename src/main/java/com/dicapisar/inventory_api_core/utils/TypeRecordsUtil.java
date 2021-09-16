package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsDTO;
import com.dicapisar.inventory_api_core.models.TypeRecords;

import java.util.ArrayList;
import java.util.List;

public class TypeRecordsUtil {
    public static List<TypeRecordsDTO> toListTypeRecordsDTO(List<TypeRecords> typeRecordsList) {
        List<TypeRecordsDTO> typeRecordsDTOList = new ArrayList<>();

        for (TypeRecords t: typeRecordsList) {
            TypeRecordsDTO typeRecordsDTO = new TypeRecordsDTO(t.getEnglishName(), t.getSpanishName(), t.getUrl());
            typeRecordsDTOList.add(typeRecordsDTO);
        }

        return typeRecordsDTOList;

    }
}
