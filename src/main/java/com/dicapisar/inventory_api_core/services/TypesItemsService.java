package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.Exeptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.dtos.requests.TypeItemRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.TypesItemsResponseDTO;
import com.dicapisar.inventory_api_core.models.TypeItems;
import com.dicapisar.inventory_api_core.repositories.ITypesItemsRepository;
import com.dicapisar.inventory_api_core.utils.TypeItemsUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypesItemsService implements ITypesItemsService {

    private ITypesItemsRepository typesItemsRepository;

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
                throw new ExistingRegistrationException("Type Item", typeItemRequestDTO.getName());
            }
        } else {
            typesItemsRepository.insertTypeItem(typeItemRequestDTO.getName(), idUser, typeItemRequestDTO.isPerishable());
        }
    }

    private boolean isRegistrationAlreadyExists(List<TypeItems> typeItemsList, String name) {
        for (TypeItems typeItems: typeItemsList) {
            if (typeItems.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
