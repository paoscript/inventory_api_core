package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.requests.ItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;

import java.util.List;

public interface IItemService {
    List<ItemResponseDTO> getListItem(boolean isActive) throws ListNotFoundException;

    ItemResponseDTO getItemById(Long idItem) throws RegisterNotFoundException;

    ItemResponseDTO updateItemById(Long idItem, ItemRequestDTO itemRequestDTO, Long idUser) throws RegisterNotFoundException;

    void createNewItem(ItemRequestDTO itemRequestDTO, Long idUser) throws ExistingRegistrationException, RegisterNotFoundException;
}
