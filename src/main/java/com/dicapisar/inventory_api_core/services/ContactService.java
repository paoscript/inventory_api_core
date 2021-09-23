package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.SimpleContactDTO;
import com.dicapisar.inventory_api_core.dtos.requests.ContactRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.models.Contact;
import com.dicapisar.inventory_api_core.models.User;
import com.dicapisar.inventory_api_core.repositories.IContactRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ContactUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Transactional
    public void createNewContact(ContactRequestDTO contactRequestDTO, Long idProvider, Long idUser) throws ExistingRegistrationException {
        List<Contact> contactList = contactRepository.getContactByNameAndIdProvider(contactRequestDTO.getName(), idProvider);

        if (!contactList.isEmpty()) {
            if (isRegistrationAlreadyExists(contactList, contactRequestDTO.getName())) {
                throw new ExistingRegistrationException("Contact", "name", contactRequestDTO.getName());
            }
        }

        contactRepository.insertContact(contactRequestDTO.getName(), contactRequestDTO.getPhoneNumber(), contactRequestDTO.getEmail(), idUser, idProvider);

    }

    @Transactional
    public ContactResponseDTO getContactResponseDTO(Long idContact) throws RegisterNotFoundException {
        Contact contact = contactRepository.findContactByIdAndActive(idContact, true);
        if (contact == null) {
            throw new RegisterNotFoundException("Contact", idContact);
        }
        return ContactUtil.toContactResponseDTO(contact);
    }

    @Transactional
    public ContactResponseDTO updateContactById(Long idContact, ContactRequestDTO contactRequestDTO, Long idUser) throws RegisterNotFoundException {
        Contact contact = contactRepository.findContactByIdAndActive(idContact, true);
        if (contact == null) {
            throw new RegisterNotFoundException("Contact", idContact);
        }
        return ContactUtil.toContactResponseDTO(updateContact(contact, contactRequestDTO, idUser));
    }

    @Transactional
    public void changeStatusContactById(Long idContact, Long idUser, Boolean status) throws RegisterNotFoundException {
        Contact contact = contactRepository.findContactByIdAndActive(idContact, !status);

        if (contact == null) {
            throw new RegisterNotFoundException("Contact", idContact);
        }

        changeStatus(contact, idUser, status);
    }


    private boolean isRegistrationAlreadyExists(List<Contact> contactList, String name) {
        for (Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private Contact updateContact(Contact contact, ContactRequestDTO contactRequestDTO, Long idUser) {
        User user = userRepository.findUserById(idUser);

        Contact contactUpdated = contact;

        if (!contactUpdated.getName().equals(contactRequestDTO.getName())){
            contactUpdated.setName(contactRequestDTO.getName());
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (contactUpdated.getPhoneNumber() == null && contactRequestDTO.getPhoneNumber() != null) {
            contactUpdated.setPhoneNumber(contactRequestDTO.getPhoneNumber());
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (contactUpdated.getPhoneNumber() != null && contactRequestDTO.getPhoneNumber() == null) {
            contactUpdated.setPhoneNumber(null);
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (contactUpdated.getPhoneNumber() == null && contactRequestDTO.getPhoneNumber() == null) {

        } else if (!contactUpdated.getPhoneNumber().equals(contactRequestDTO.getPhoneNumber())) {
            contactUpdated.setPhoneNumber(contactRequestDTO.getPhoneNumber());
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (contactUpdated.getEmail() == null && contactRequestDTO.getEmail() != null) {
            contactUpdated.setEmail(contactRequestDTO.getEmail());
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (contactUpdated.getEmail() != null && contactRequestDTO.getEmail() == null) {
            contactUpdated.setEmail(null);
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (contactUpdated.getEmail() == null && contactRequestDTO.getEmail() == null) {

        } else if (!contactUpdated.getEmail().equals(contactRequestDTO.getEmail())) {
            contactUpdated.setEmail(contactRequestDTO.getEmail());
            contactUpdated.setUpdater(user);
            contactUpdated.setUpdatedAt(LocalDateTime.now());
        }

        return contactRepository.save(contactUpdated);

    }

    private void changeStatus(Contact contact, Long idUser, Boolean status) {
        User user = userRepository.findUserById(idUser);

        Contact contactUpdated = contact;

        contactUpdated.setActive(status);
        contactUpdated.setUpdater(user);
        contactUpdated.setUpdatedAt(LocalDateTime.now());

        contactRepository.save(contactUpdated);
    }
}
