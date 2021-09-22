package com.dicapisar.inventory_api_core.dtos.resposes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypesItemsResponseDTO {
    private Long id;
    private String name;
}
