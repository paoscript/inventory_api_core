package com.dicapisar.inventory_api_core.dtos.resposes;

import com.dicapisar.inventory_api_core.dtos.SimpleContactDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListContactResponseDTO {
    private ProviderResponseDTO provider;
    private List<SimpleContactDTO> contacts = new ArrayList<>();
}
