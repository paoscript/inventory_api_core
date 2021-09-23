package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.Exeptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.Exeptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.TypeItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.TypesItemsResponseDTO;

import java.util.List;

public interface ITypesItemsService {
    List<TypesItemsResponseDTO> getListTypesItems(boolean isActive) throws ListNotFoundException;
    void createNewTypeItem(TypeItemRequestDTO typeItemRequestDTO, Long idUser) throws ExistingRegistrationException;
    TypesItemsResponseDTO getTypeItemRequestDTO(Long idTypeItem) throws RegisterNotFoundException;
    TypesItemsResponseDTO updateTypeItemById(Long idTypeItem, TypeItemRequestDTO typeItemRequestDTO, Long idUser) throws RegisterNotFoundException;
    void changeStatusActiveById(Long idTypeItem, Long idUser, Boolean status) throws RegisterNotFoundException;
}
