package com.dicapisar.inventory_api_core.services;

import com.dicapisar.inventory_api_core.dtos.requests.ProviderRequestDTO;
import com.dicapisar.inventory_api_core.dtos.resposes.ProviderResponseDTO;
import com.dicapisar.inventory_api_core.exceptions.ExistingRegistrationException;
import com.dicapisar.inventory_api_core.exceptions.ListNotFoundException;
import com.dicapisar.inventory_api_core.exceptions.RegisterNotFoundException;
import com.dicapisar.inventory_api_core.models.Provider;
import com.dicapisar.inventory_api_core.models.User;
import com.dicapisar.inventory_api_core.repositories.IProviderRepository;
import com.dicapisar.inventory_api_core.repositories.IUserRepository;
import com.dicapisar.inventory_api_core.utils.ProviderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ProviderResponseDTO updateProviderById(Long idProvider, ProviderRequestDTO providerRequestDTO, Long idUser) throws RegisterNotFoundException {
        Provider provider = providerRepository.findProviderByIdAndActive(idProvider, true);
        if (provider == null) {
            throw new RegisterNotFoundException("Provider", idProvider);
        }
        return ProviderUtil.toProviderResponseDTO(updateProvider(provider, providerRequestDTO, idUser));
    }

    public void changeStatusActiveById(Long idProvider, Long idUser, Boolean status) throws RegisterNotFoundException {
        Provider provider = providerRepository.findProviderByIdAndActive(idProvider, !status);
        if (provider == null) {
            throw new RegisterNotFoundException("Provider", idProvider);
        }
        changeStatusActivate(provider, idUser, status);
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

    private Provider updateProvider(Provider provider, ProviderRequestDTO providerRequestDTO, Long idUser) {
        User user = userRepository.findUserById(idUser);

        Provider providerUpdated = provider;

        if (!providerUpdated.getName().equals(providerRequestDTO.getName())) {
            providerUpdated.setName(providerRequestDTO.getName());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (!providerUpdated.getDocumentNumber().equals(providerRequestDTO.getDocumentNumber())) {
            providerUpdated.setDocumentNumber(providerRequestDTO.getDocumentNumber());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (!providerUpdated.getPhoneNumber().equals(providerRequestDTO.getPhoneNumber())) {
            providerUpdated.setPhoneNumber(providerRequestDTO.getPhoneNumber());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if(providerUpdated.getEmail() == null && providerRequestDTO.getEmail() != null) {
            providerUpdated.setEmail(providerRequestDTO.getEmail());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (providerUpdated.getEmail() != null && providerRequestDTO.getEmail() == null) {
            providerUpdated.setEmail(null);
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (providerUpdated.getEmail() == null && providerRequestDTO.getEmail() == null){

        } else if (!providerUpdated.getEmail().equals(providerRequestDTO.getEmail())) {
            providerUpdated.setEmail(providerRequestDTO.getEmail());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        }

        if (providerUpdated.getAddress() == null && providerRequestDTO.getAddress() != null) {
            providerUpdated.setAddress(providerRequestDTO.getAddress());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (providerUpdated.getAddress() != null && providerRequestDTO.getAddress() == null) {
            providerUpdated.setAddress(null);
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        } else if (providerUpdated.getAddress() == null && providerRequestDTO.getAddress() == null) {

        } else if (!providerUpdated.getAddress().equals(providerRequestDTO.getAddress())) {
            providerUpdated.setAddress(providerRequestDTO.getAddress());
            providerUpdated.setUpdater(user);
            providerUpdated.setUpdatedAt(LocalDateTime.now());
        }

        return providerRepository.save(providerUpdated);
    }

    private void changeStatusActivate(Provider provider, Long idUser, Boolean status) {
        User user = userRepository.findUserById(idUser);

        Provider providerUpdated = provider;

        providerUpdated.setActive(status);
        providerUpdated.setUpdater(user);
        providerUpdated.setUpdatedAt(LocalDateTime.now());

        providerRepository.save(providerUpdated);
    }
}
