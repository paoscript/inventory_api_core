package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsResponseDTO;
import com.dicapisar.inventory_api_core.services.IManagementService;
import com.dicapisar.inventory_api_core.utils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/management")
public class ManagementController {

    private IManagementService managementService;

    @GetMapping("/list")
    public ResponseEntity< List<TypeRecordsResponseDTO>> getListTypeRecords(HttpSession session) {
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("ADMIN");
        if( !SessionUtil.isSessionExist(session)) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if( !SessionUtil.isSessionWithPermissions(session, permissionList)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managementService.getListTypeRecordsDTO(), HttpStatus.OK);
    }


}
