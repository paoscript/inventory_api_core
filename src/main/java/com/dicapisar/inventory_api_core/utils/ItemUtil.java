package com.dicapisar.inventory_api_core.utils;

import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.models.Item;

public class ItemUtil {

    public static ItemResponseDTO toItemResponseDTO(Item item) {
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();

        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setName(item.getName());
        itemResponseDTO.setCount(item.getCount());
        itemResponseDTO.setAverageCost(item.getAverageCost());
        itemResponseDTO.setPrice(item.getPrice());
        itemResponseDTO.setBarcode(item.getBarcode());
        itemResponseDTO.setQrCode(item.getQrCode());
        itemResponseDTO.setTypeItem(TypeItemsUtil.toTypesItemsResponseDTO(item.getTypeItem()));
        itemResponseDTO.setBrand(BrandUtil.toBrandResponseDTO(item.getBrand()));

        return itemResponseDTO;
    }
}
