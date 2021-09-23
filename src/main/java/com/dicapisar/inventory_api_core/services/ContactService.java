package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.models.Contact;
import com.dicapisar.inventory_api_core.repositories.IContactRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ContactUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactService implements IContactService {

    private IContactRepository contactRepository;
    private IUserRepository userRepository;

    @Transactional
    public List<ContactResponseDTO> getListContact(boolean isActive) throws ListNotFoundException {
        List<Contact> contactList = contactRepository.getListContact(isActive);

        if (contactList.isEmpty()) {
            throw new ListNotFoundException("Contact");
        }

        List<ContactResponseDTO> contactResponseDTOList = new ArrayList<>();

        for (Contact contact : contactList) {
            contactResponseDTOList.add(ContactUtil.toContactResponseDTO(contact));
        }

        return contactResponseDTOList;
    }

    @Transactional
    public List<ListContactResponseDTO> getListOfListContactResponseDTO(boolean isActive) throws ListNotFoundException {
        List<Contact> contactList = contactRepository.getListContact(isActive);

        if (contactList.isEmpty()) {
            throw new ListNotFoundException("Contact");
        }

        return ContactUtil.toListContactResponseDTO(contactList);

    }

    @Transactional
    public ListContactResponseDTO getListContactByProvider(boolean isActive, Long idProvider) throws ListNotFoundException {
        List<Contact> contactList = contactRepository.getListContactByIdProvider(isActive, idProvider);

        if (contactList.isEmpty()) {
            throw new ListNotFoundException("Contact");
        }

        return ContactUtil.toOnlyListContactResponseDTO(contactList);

    }

}
