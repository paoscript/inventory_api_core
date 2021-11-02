package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.TypeItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.TypesItemsResponseDTO;
import com.dicapisar.inventory_api_core.services.ITypesItemsService;
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
@RequestMapping("/types_items")
public class TypesItemsController {

    private ITypesItemsService typesItemsService;

    @GetMapping("list")
    public ResponseEntity<List<TypesItemsResponseDTO>> getListTypesItems(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive, HttpSession session) throws ListNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(typesItemsService.getListTypesItems(isActive), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTypeItem(@RequestBody @Valid TypeItemRequestDTO typeItemRequestDTO, HttpSession session) throws ExistingRegistrationException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        typesItemsService.createNewTypeItem(typeItemRequestDTO, 1L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypesItemsResponseDTO> getTypeItemById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(typesItemsService.getTypeItemRequestDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TypesItemsResponseDTO> updateTypeItemById(@PathVariable long id, @RequestBody @Valid TypeItemRequestDTO typeItemRequestDTO, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(typesItemsService.updateTypeItemById(id, typeItemRequestDTO, 1L), HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateTypeItemById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        typesItemsService.changeStatusActiveById(id, 1L, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateTypeItemById(@PathVariable Long id, HttpSession session) throws RegisterNotFoundException {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        typesItemsService.changeStatusActiveById(id, 1L, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
