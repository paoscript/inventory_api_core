package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.SimpleContactDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactUtil {
    public static ContactResponseDTO toContactResponseDTO(Contact contact) {
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();

        contactResponseDTO.setId(contact.getId());
        contactResponseDTO.setName(contact.getName());
        contactResponseDTO.setPhoneNumber(contact.getPhoneNumber());
        contactResponseDTO.setEmail(contact.getEmail());
        contactResponseDTO.setProvider(ProviderUtil.toProviderResponseDTO(contact.getProvider()));

        return contactResponseDTO;
    }

    public static ListContactResponseDTO toOnlyListContactResponseDTO(List<Contact> contactList) {
        ListContactResponseDTO listContactResponseDTO = new ListContactResponseDTO();
        listContactResponseDTO.setProvider(ProviderUtil.toProviderResponseDTO(contactList.get(0).getProvider()));

        listContactResponseDTO.setContacts(new ArrayList<>());

        for (Contact contact: contactList) {
            listContactResponseDTO.getContacts().add(toSimpleContactDTO(contact));
        }

        return listContactResponseDTO;
    }

    public static List<ListContactResponseDTO> toListContactResponseDTO(List<Contact> contactList) {
        List<ListContactResponseDTO> listContactResponseDTOS = new ArrayList<>();

        for (int i = 0; i < contactList.size(); i++) {
            if (listContactResponseDTOS.isEmpty()) {
                listContactResponseDTOS.add(new ListContactResponseDTO());
                listContactResponseDTOS.get(0).setProvider(ProviderUtil.toProviderResponseDTO(contactList.get(i).getProvider()));
                listContactResponseDTOS.get(0).getContacts().add(toSimpleContactDTO(contactList.get(i)));
            } else {
                if (!isProviderExist(listContactResponseDTOS, contactList.get(i).getProvider().getName())){
                    ListContactResponseDTO listContactResponseDTO = new ListContactResponseDTO();
                    listContactResponseDTO.setProvider(ProviderUtil.toProviderResponseDTO(contactList.get(i).getProvider()));
                    listContactResponseDTO.getContacts().add(toSimpleContactDTO(contactList.get(i)));
                    listContactResponseDTOS.add(listContactResponseDTO);
                } else {
                    setContact(listContactResponseDTOS, contactList.get(i).getProvider().getName(), contactList.get(i));
                }
            }
        }

        return listContactResponseDTOS;
    }

    public static SimpleContactDTO toSimpleContactDTO(Contact contact) {
        SimpleContactDTO simpleContactDTO = new SimpleContactDTO();

        simpleContactDTO.setId(contact.getId());
        simpleContactDTO.setName(contact.getName());
        simpleContactDTO.setPhoneNumber(contact.getPhoneNumber());
        simpleContactDTO.setEmail(contact.getEmail());

        return simpleContactDTO;
    }

    private static boolean isProviderExist(List<ListContactResponseDTO> listContactResponseDTOS, String nameProvider) {
        for (ListContactResponseDTO listContactResponseDTO: listContactResponseDTOS) {
            if (listContactResponseDTO.getProvider().getName().equals(nameProvider)){
                return true;
            }
        }
        return false;
    }

    private static void setContact(List<ListContactResponseDTO> listContactResponseDTOS, String nameProvider, Contact contact) {
        for (ListContactResponseDTO listContactResponseDTO: listContactResponseDTOS) {
            if (listContactResponseDTO.getProvider().getName().equals(nameProvider)){
                listContactResponseDTO.getContacts().add(toSimpleContactDTO(contact));
            }
        }
    }
}
