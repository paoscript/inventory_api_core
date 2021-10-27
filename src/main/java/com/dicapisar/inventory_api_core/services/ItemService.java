package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.requests.ItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ItemResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.models.Brand;
import com.dicapisar.inventory_api_core.models.Item;
import com.dicapisar.inventory_api_core.models.TypeItems;
import com.dicapisar.inventory_api_core.models.User;
import com.dicapisar.inventory_api_core.repositories.IBrandRepository;
import com.dicapisar.inventory_api_core.repositories.IItemRepository;
import com.dicapisar.inventory_api_core.repositories.ITypesItemsRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ItemUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements IItemService{

    private IItemRepository itemRepository;
    private IUserRepository userRepository;
    private IBrandRepository brandRepository;
    private ITypesItemsRepository typesItemsRepository;

    @Transactional
    public List<ItemResponseDTO> getListItem(boolean isActive) throws ListNotFoundException {
        List<Item> itemList = itemRepository.getListBrand(isActive);

        if (itemList.isEmpty()) {
            throw new ListNotFoundException("Item");
        }

        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();

        for (Item item : itemList) {
            itemResponseDTOList.add(ItemUtil.toItemResponseDTO(item));
        }

        return itemResponseDTOList;
    }

    @Transactional
    public ItemResponseDTO getItemById(Long idItem) throws RegisterNotFoundException {
        Item item = itemRepository.findItemByIdAndActive(idItem, true);
        if(item == null) {
            throw new RegisterNotFoundException("Item", idItem);
        }
        return ItemUtil.toItemResponseDTO(item);
    }

    @Transactional
    public ItemResponseDTO updateItemById(Long idItem, ItemRequestDTO itemRequestDTO, Long idUser) throws RegisterNotFoundException {
        Item item = itemRepository.findItemByIdAndActive(idItem, true);
        if (item == null) {
            throw new RegisterNotFoundException("Item", idItem);
        }

        return ItemUtil.toItemResponseDTO(updateItem(item, itemRequestDTO, idUser));
    }

    private Item updateItem(Item item, ItemRequestDTO itemRequestDTO, Long idUser) throws RegisterNotFoundException {
        User user = userRepository.findUserById(idUser);

        Item itemUpdated = item;

        boolean hasChanges = false;

        if(item.getName() != itemRequestDTO.getName()) {
            itemUpdated.setName(itemRequestDTO.getName());
            hasChanges = true;
        }
        if (item.getPrice() != itemRequestDTO.getPrice()) {
            itemUpdated.setPrice(itemRequestDTO.getPrice());
            hasChanges = true;
        }
        if (item.getBarcode() != itemRequestDTO.getBarcode()) {
            itemUpdated.setBarcode(itemRequestDTO.getBarcode());
            hasChanges = true;
        }
        if (item.getQrCode() != itemRequestDTO.getQrCode()) {
            itemUpdated.setQrCode(itemRequestDTO.getQrCode());
            hasChanges = true;
        }
        if (item.getTypeItem().getId() != itemRequestDTO.getIdTypeItem()) {
            TypeItems typeItems = typesItemsRepository.findTypeItemsByIdAndActive(itemRequestDTO.getIdTypeItem(), true);
            if (typeItems == null) {
                throw new RegisterNotFoundException("Type Item", itemRequestDTO.getIdTypeItem());
            }
            hasChanges = true;
            itemUpdated.setTypeItem(typeItems);
        }
        if (item.getBrand().getId() != itemRequestDTO.getIdBrand()) {
            Brand brand = brandRepository.findBrandByIdAndActive(itemRequestDTO.getIdBrand(), true);
            if (brand == null) {
                throw new RegisterNotFoundException("Brand", itemRequestDTO.getIdBrand());
            }
            hasChanges = true;
            itemUpdated.setBrand(brand);
        }

        if (hasChanges) {
            itemUpdated.setUpdater(user);
            itemUpdated.setUpdatedAt(LocalDateTime.now());
        }

        return itemRepository.save(itemUpdated);

    }
}
