package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.Exeptions.ListNotFoundException;
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
}
