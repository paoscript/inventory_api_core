package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.TypeItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.TypesItemsResponseDTO;
import com.dicapisar.inventory_api_core.models.TypeItems;
import com.dicapisar.inventory_api_core.models.User;
import com.dicapisar.inventory_api_core.repositories.ITypesItemsRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.TypeItemsUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypesItemsService implements ITypesItemsService {

    private ITypesItemsRepository typesItemsRepository;
    private IUserRepository userRepository;

    public List<TypesItemsResponseDTO> getListTypesItems(boolean isActive) throws ListNotFoundException {
        List<TypeItems> typeItemsList = typesItemsRepository.getListTypesItems(isActive);

        if (typeItemsList.isEmpty()) {
            throw new ListNotFoundException("Types Items");
        }

        List<TypesItemsResponseDTO> typesItemsResponseDTOList = new ArrayList<>();

        for (TypeItems typeItems: typeItemsList) {
            typesItemsResponseDTOList.add(TypeItemsUtil.toTypesItemsResponseDTO(typeItems));
        }

        return typesItemsResponseDTOList;
    }

    public void createNewTypeItem(TypeItemRequestDTO typeItemRequestDTO, Long idUser) throws ExistingRegistrationException {
        List<TypeItems> typeItemsList = typesItemsRepository.getItemTypeByName(typeItemRequestDTO.getName());

        if (!typeItemsList.isEmpty()) {
            if (isRegistrationAlreadyExists(typeItemsList, typeItemRequestDTO.getName())) {
                throw new ExistingRegistrationException("Type Item", "name", typeItemRequestDTO.getName());
            }
        }

        typesItemsRepository.insertTypeItem(typeItemRequestDTO.getName(), idUser, typeItemRequestDTO.isPerishable());

    }

    public TypesItemsResponseDTO getTypeItemRequestDTO(Long idTypeItem) throws RegisterNotFoundException {
        TypeItems typeItems = typesItemsRepository.findTypeItemsByIdAndActive(idTypeItem, true);
        if (typeItems == null) {
            throw new RegisterNotFoundException("Type Item", idTypeItem);
        }
        return TypeItemsUtil.toTypesItemsResponseDTO(typeItems);
    }

    public TypesItemsResponseDTO updateTypeItemById(Long idTypeItem, TypeItemRequestDTO typeItemRequestDTO, Long idUser) throws RegisterNotFoundException {
       TypeItems typeItems = typesItemsRepository.findTypeItemsByIdAndActive(idTypeItem, true);
       if(typeItems == null) {
           throw new RegisterNotFoundException("Type Item", idTypeItem);
       }

       return TypeItemsUtil.toTypesItemsResponseDTO(updateTypeItem(typeItems, typeItemRequestDTO, idUser));
    }

    public void changeStatusActiveById(Long idTypeItem, Long idUser, Boolean status) throws RegisterNotFoundException {
        TypeItems typeItems = typesItemsRepository.findTypeItemsByIdAndActive(idTypeItem, !status);
        if (typeItems == null) {
            throw new RegisterNotFoundException("Type Item", idTypeItem);
        }

        changeStatus(typeItems, idUser, status);
    }

    private boolean isRegistrationAlreadyExists(List<TypeItems> typeItemsList, String name) {
        for (TypeItems typeItems: typeItemsList) {
            if (typeItems.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private TypeItems updateTypeItem(TypeItems typeItems, TypeItemRequestDTO typeItemRequestDTO, Long idUser) {

        User user = userRepository.findUserById(idUser);

        TypeItems typeItemsUpdated = typeItems;

        if(!typeItemsUpdated.getName().equals(typeItemRequestDTO.getName())) {
            typeItemsUpdated.setName(typeItemRequestDTO.getName());
            typeItemsUpdated.setUpdater(user);
            typeItemsUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (!typeItemsUpdated.isPerishable() == typeItemRequestDTO.isPerishable()) {
            typeItemsUpdated.setPerishable(typeItemRequestDTO.isPerishable());
            typeItemsUpdated.setUpdater(user);
            typeItemsUpdated.setUpdatedAt(LocalDateTime.now());
        }

        return typesItemsRepository.save(typeItemsUpdated);
    }

    private void changeStatus(TypeItems typeItems, Long idUser, Boolean status) {
        User user = userRepository.findUserById(idUser);

        TypeItems typeItemsUpdated = typeItems;

        typeItemsUpdated.setActive(status);
        typeItemsUpdated.setUpdater(user);
        typeItemsUpdated.setUpdatedAt(LocalDateTime.now());

        typesItemsRepository.save(typeItemsUpdated);
    }
}
