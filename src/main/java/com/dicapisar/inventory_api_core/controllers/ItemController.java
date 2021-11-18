package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.requests.ItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ErrorUserWithoutPermissions;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.services.IItemService;
import com.dicapisar.inventory_api_core.utils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.inventory_api_core.commons.APIInventoryCoreConstants.ADMIN;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private IItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<List<ItemResponseDTO>> getListItems(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive,
                                                              HttpSession session)
            throws ListNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        return new ResponseEntity<>(itemService.getListItem(isActive), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id, HttpSession session)
            throws RegisterNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItemById(@PathVariable Long id,
                                                          @RequestBody @Valid ItemRequestDTO itemRequestDTO,
                                                          HttpSession session)
            throws RegisterNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        return new ResponseEntity<>(itemService.updateItemById(id, itemRequestDTO, SessionUtil.getUserId(session)),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewItem(@RequestBody @Valid ItemRequestDTO itemRequestDTO, HttpSession session)
            throws ExistingRegistrationException, RegisterNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        itemService.createNewItem(itemRequestDTO, SessionUtil.getUserId(session));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateItemById(@PathVariable Long id, HttpSession session)
            throws RegisterNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        itemService.changeStatusActiveById(id, SessionUtil.getUserId(session), false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateItemById(@PathVariable Long id, HttpSession session)
            throws RegisterNotFoundException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        itemService.changeStatusActiveById(id, SessionUtil.getUserId(session), true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
