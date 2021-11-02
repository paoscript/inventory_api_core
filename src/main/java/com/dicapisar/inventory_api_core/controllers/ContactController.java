package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.requests.ContactRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ContactResponseDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ListContactResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.services.IContactService;
import com.dicapisar.inventory_api_core.utils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private IContactService contactService;

    @GetMapping("/list")
    public ResponseEntity<List<ContactResponseDTO>> getListContacts(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive, HttpSession session) throws ListNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contactService.getListContact(isActive), HttpStatus.OK);
    }

    @GetMapping("/list/provider")
    public ResponseEntity<List<ListContactResponseDTO>> getListOfListContactResponseDTO(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive, HttpSession session) throws ListNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contactService.getListOfListContactResponseDTO(isActive), HttpStatus.OK);
    }

    @GetMapping("/list/provider/{id}")
    public ResponseEntity<ListContactResponseDTO> getListContactsByProvider(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive, @PathVariable Long id, HttpSession session) throws ListNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contactService.getListContactByProvider(isActive, id), HttpStatus.OK);
    }

    @PostMapping("/create/provider/{id}")
    public ResponseEntity<?> createNewContact(@PathVariable Long id, @RequestBody @Valid ContactRequestDTO contactRequestDTO, HttpSession session) throws ExistingRegistrationException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        contactService.createNewContact(contactRequestDTO, id, 1L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contactService.getContactResponseDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContactById(@PathVariable Long id, @RequestBody @Valid ContactRequestDTO contactRequestDTO, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(contactService.updateContactById(id, contactRequestDTO, 1L), HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateContactById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        contactService.changeStatusContactById(id, 1L, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateContactById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        contactService.changeStatusContactById(id, 1L, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
