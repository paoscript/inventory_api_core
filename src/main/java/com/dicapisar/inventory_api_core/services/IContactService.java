package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IContactService {
    List<ContactResponseDTO> getListContact(boolean isActive) throws ListNotFoundException;
    ListContactResponseDTO getListContactByProvider(boolean isActive, Long idProvider) throws ListNotFoundException;
    List<ListContactResponseDTO> getListOfListContactResponseDTO(boolean isActive) throws ListNotFoundException;
}
