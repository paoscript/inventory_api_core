package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.models.Item;
import com.dicapisar.inventory_api_core.repositories.IItemRepository;
import com.dicapisar.inventory_api_core.utils.ItemUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements IItemService{

    private IItemRepository iItemRepository;

    @Transactional
    public List<ItemResponseDTO> getListItem(boolean isActive) throws ListNotFoundException {
        List<Item> itemList = iItemRepository.getListBrand(isActive);

        if (itemList.isEmpty()) {
            throw new ListNotFoundException("Item");
        }

        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();

        for (Item item : itemList) {
            itemResponseDTOList.add(ItemUtil.toItemResponseDTO(item));
        }

        return itemResponseDTOList;
    }
}
