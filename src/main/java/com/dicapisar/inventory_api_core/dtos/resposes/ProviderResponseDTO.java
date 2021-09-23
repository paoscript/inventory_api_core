package com.dicapisar.inventory_api_core.dtos.resposes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderResponseDTO {
    private String name;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private String address;
}
