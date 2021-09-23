package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.requests.ContactRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.services.IContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private IContactService contactService;

    @GetMapping("/list")
    public ResponseEntity<List<ContactResponseDTO>> getListContacts(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) throws ListNotFoundException {
        return new ResponseEntity<>(contactService.getListContact(isActive), HttpStatus.OK);
    }

    @GetMapping("/list/provider")
    public ResponseEntity<List<ListContactResponseDTO>> getListOfListContactResponseDTO(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) throws ListNotFoundException {
        return new ResponseEntity<>(contactService.getListOfListContactResponseDTO(isActive), HttpStatus.OK);
    }

    @GetMapping("/list/provider/{id}")
    public ResponseEntity<ListContactResponseDTO> getListContactsByProvider(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive, @PathVariable Long id) throws ListNotFoundException {
        return new ResponseEntity<>(contactService.getListContactByProvider(isActive, id), HttpStatus.OK);
    }

    @PostMapping("/create/provider/{id}")
    public ResponseEntity<?> createNewContact(@PathVariable Long id, @RequestBody @Valid ContactRequestDTO contactRequestDTO) throws ExistingRegistrationException {
        contactService.createNewContact(contactRequestDTO, id, 1L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Long id) throws RegisterNotFoundException {
        return new ResponseEntity<>(contactService.getContactResponseDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContactById(@PathVariable Long id, @RequestBody @Valid ContactRequestDTO contactRequestDTO) throws RegisterNotFoundException {
        return new ResponseEntity<>(contactService.updateContactById(id, contactRequestDTO, 1L), HttpStatus.OK);
    }

}
