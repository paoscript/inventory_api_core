package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.requests.ProviderRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.models.Provider;
import com.dicapisar.inventory_api_core.repositories.IProviderRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ProviderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProviderService implements IProviderService {

    private IProviderRepository providerRepository;
    private IUserRepository userRepository;

    public List<ProviderResponseDTO> getListProvider(boolean isActive) throws ListNotFoundException {
        List<Provider> providerList = providerRepository.getListProvider(isActive);

        if (providerList.isEmpty()) {
            throw new ListNotFoundException("Provider");
        }

        List<ProviderResponseDTO> providerResponseDTOList = new ArrayList<>();

        for (Provider provider : providerList) {
            providerResponseDTOList.add(ProviderUtil.toProviderResponseDTO(provider));
        }

        return providerResponseDTOList;
    }

    public void createNewProvider(ProviderRequestDTO providerRequestDTO, Long idUser) throws ExistingRegistrationException {
        List<Provider> providerList = providerRepository.getProviderByName(providerRequestDTO.getName());

        if (!providerList.isEmpty()) {
            if (isRegistrationAlreadyExistsByName(providerList, providerRequestDTO.getName())) {
                throw new ExistingRegistrationException("Provider", "name", providerRequestDTO.getName());
            }
            if (isRegistrationAlreadyExistsByDocumentNumber(providerList, providerRequestDTO.getDocumentNumber())) {
                throw new ExistingRegistrationException("Provider", "documentNumber", providerRequestDTO.getDocumentNumber());
            }
        }

        providerRepository.insertProvider(providerRequestDTO.getName(), providerRequestDTO.getDocumentNumber(), providerRequestDTO.getPhoneNumber(), providerRequestDTO.getEmail(), providerRequestDTO.getAddress(), idUser);

    }

    public ProviderResponseDTO getProviderResponseDTO(Long idProvider) throws RegisterNotFoundException {
        Provider provider = providerRepository.findProviderByIdAndActive(idProvider, true);
        if (provider == null) {
            throw new RegisterNotFoundException("Provider", idProvider);
        }
        return ProviderUtil.toProviderResponseDTO(provider);
    }

    private boolean isRegistrationAlreadyExistsByName(List<Provider> providerList, String name) {
        for (Provider provider : providerList) {
            if (provider.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean isRegistrationAlreadyExistsByDocumentNumber(List<Provider> providerList, String documentNumber) {
        for (Provider provider : providerList) {
            if (provider.getDocumentNumber().equals(documentNumber)){
                return true;
            }
        }
        return false;
    }
}
