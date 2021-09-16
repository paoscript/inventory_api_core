package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.resposes.TypeRecordsDTO;
import com.dicapisar.inventory_api_core.services.IManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/management")
public class ManagementController {

    private IManagementService managementService;

    @GetMapping("/list")
    public ResponseEntity< List<TypeRecordsDTO>> getListTypeRecords() {
        return new ResponseEntity<>(managementService.getListTypeRecordsDTO(), HttpStatus.OK);
    }


}
