package com.dicapisar.inventory_api_core.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeItemRequestDTO {
    @NotEmpty(message = "The name must not be empty.")
    private String name;

    @NotNull(message = "The Type Item must not be empty, must be true or false")
    private boolean perishable;
}
