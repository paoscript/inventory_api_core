package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;

import java.util.List;

public interface IItemService {
    List<ItemResponseDTO> getListItem(boolean isActive) throws ListNotFoundException;
}
