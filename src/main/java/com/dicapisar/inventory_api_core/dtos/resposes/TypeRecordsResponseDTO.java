package com.dicapisar.inventory_api_core.dtos.resposes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeRecordsResponseDTO {
    private String englishName;
    private String spanishName;
    private String url;
}
