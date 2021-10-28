package com.dicapisar.inventory_api_core.controllers;

import com.dicapisar.inventory_api_core.dtos.requests.ItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.services.IItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private IItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<List<ItemResponseDTO>> getListItems(@RequestParam(name = "isActive", defaultValue = "true") Boolean isActive) throws ListNotFoundException {
        return new ResponseEntity<>(itemService.getListItem(isActive), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) throws RegisterNotFoundException {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItemById(@PathVariable Long id, @RequestBody @Valid ItemRequestDTO itemRequestDTO) throws RegisterNotFoundException {
        return new ResponseEntity<>(itemService.updateItemById(id, itemRequestDTO, 1L), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewItem(@RequestBody @Valid ItemRequestDTO itemRequestDTO) throws ExistingRegistrationException, RegisterNotFoundException {
        itemService.createNewItem(itemRequestDTO, 1L);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateItemById(@PathVariable Long id) throws RegisterNotFoundException {
        itemService.changeStatusActiveById(id, 1L, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateItemById(@PathVariable Long id) throws RegisterNotFoundException {
        itemService.changeStatusActiveById(id, 1L, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}