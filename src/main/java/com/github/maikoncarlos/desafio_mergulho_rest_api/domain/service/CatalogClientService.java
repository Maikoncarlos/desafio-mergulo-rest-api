package com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.exception.BusinessEmailException;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Entrega;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CatalogClientService {

    private final ClientRepository clienteRepository;

    public Cliente getCliente(Entrega entrega) {
        return clienteRepository.findById(entrega.getCliente().getId())
                .orElseThrow(() -> new BusinessEmailException(" Cliente não encontrado! "));
    }

    @Transactional
    public Cliente persistInClient(Cliente cliente) throws BusinessEmailException {
        validEmailExist(cliente);
        return clienteRepository.save(cliente);
    }
    @Transactional
    public void deletedClient(Long clientId){
        clienteRepository.deleteById(clientId);
    }

    private void validEmailExist(Cliente cliente) {
        boolean emailInUse = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clientExist -> !clientExist.equals(cliente));

        if(emailInUse){
            throw new BusinessEmailException(" Já existente um cliente cadastrado com este email! ");
        }
    }
}
