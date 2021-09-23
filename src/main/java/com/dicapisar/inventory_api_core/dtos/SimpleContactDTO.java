package com.dicapisar.inventory_api_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleContactDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
}
