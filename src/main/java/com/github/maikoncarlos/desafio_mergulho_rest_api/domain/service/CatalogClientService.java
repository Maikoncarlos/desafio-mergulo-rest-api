package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.exception.BusinessEmailException;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CatalogClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Cliente persistInClien(Cliente cliente) throws BusinessEmailException {
        validEmailExist(cliente);
        return clientRepository.save(cliente);
    }
    @Transactional
    public void deletedClient(Long clientId){
        clientRepository.deleteById(clientId);
    }

    private void validEmailExist(Cliente cliente) {
        boolean emailInUse = clientRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clientExist -> !clientExist.equals(cliente));

        if(emailInUse){
            throw new BusinessEmailException(" Já existente um cliente cadastrado com este email! ");
        }
    }
}
