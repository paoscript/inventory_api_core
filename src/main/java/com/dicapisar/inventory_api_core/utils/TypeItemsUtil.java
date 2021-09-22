package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.TypesItemsResponseDTO;
import com.dicapisar.inventory_api_core.models.TypeItems;


public class TypeItemsUtil {
    public static TypesItemsResponseDTO toTypesItemsResponseDTO(TypeItems typeItems) {
        TypesItemsResponseDTO typesItemsResponseDTO = new TypesItemsResponseDTO();

        typesItemsResponseDTO.setId(typeItems.getId());
        typesItemsResponseDTO.setName(typeItems.getName());

        return typesItemsResponseDTO;
    }
}
