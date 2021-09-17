package com.dicapisar.inventory_api_core.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequestDTO {
    @NotEmpty(message = "The name must not be empty.")
    private String name;
}
