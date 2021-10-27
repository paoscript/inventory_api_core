package com.dicapisar.inventory_api_core.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    @NotEmpty(message = "The name must not be empty.")
    private String name;
    @Min(value = 0, message = "The price cannot be less than zero.")
    private int price;
    private String barcode;
    private String qrCode;
    @NotNull(message = "The idTypeItem must not be null.")
    private Long idTypeItem;
    @NotNull(message = "The idBrand must not be null.")
    private Long idBrand;
}
