package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.requests.ProviderRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ErrorUserWithoutPermissions;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.services.IProviderService;
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
@RequestMapping("/provider")
public class ProviderController {

    private IProviderService providerService;

    @GetMapping("/list")
    public ResponseEntity<List<ProviderResponseDTO>> getListProvider(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive,
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
        return new ResponseEntity<>(providerService.getListProvider(isActive), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewProvider(@RequestBody @Valid ProviderRequestDTO providerRequestDTO,
                                               HttpSession session)
            throws ExistingRegistrationException, ErrorUserWithoutPermissions {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add(ADMIN);
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            throw new ErrorUserWithoutPermissions();
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            throw new ErrorUserWithoutPermissions();
        }
        providerService.createNewProvider(providerRequestDTO, SessionUtil.getUserId(session));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> getProviderResponseDTOById(@PathVariable Long id, HttpSession session)
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
        return new ResponseEntity<>(providerService.getProviderResponseDTO(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> updateProviderById(@PathVariable Long id,
                                                                  @RequestBody @Valid ProviderRequestDTO providerRequestDTO,
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
        return new ResponseEntity<>(providerService.updateProviderById(id, providerRequestDTO,
                SessionUtil.getUserId(session)), HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateProviderById(@PathVariable Long id, HttpSession session)
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
        providerService.changeStatusActiveById(id, SessionUtil.getUserId(session), true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateProviderById(@PathVariable Long id, HttpSession session)
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
        providerService.changeStatusActiveById(id, SessionUtil.getUserId(session), false);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
