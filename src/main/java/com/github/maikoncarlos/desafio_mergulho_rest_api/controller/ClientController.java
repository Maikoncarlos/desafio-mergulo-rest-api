package com.github.maikoncarlos.desafio_mergulho_rest_api.controller;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.service.CatalogClientService;
import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.exception.BusinessEmailException;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;
    private final CatalogClientService catalogClientService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientList() {
        return ResponseEntity.ok().body(clientRepository.findByNameContains("maik"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> searchClient(@PathVariable("id") Long clientId) {
        return ResponseEntity.ok().body(validClientExist(clientId));
    }

    @PostMapping()
    public ResponseEntity<Cliente> createdClient(@RequestBody @Valid Cliente cliente) throws BusinessEmailException {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogClientService.persistInClien(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable("id") Long clientId ,
                                                @RequestBody @Valid Cliente cliente){

        validClientExist(clientId);
        cliente.setId(clientId);
        return ResponseEntity.ok().body(clientRepository.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long clientId) {
        validClientExist(clientId);
        catalogClientService.deletedClient(clientId);
        return ResponseEntity.noContent().build();
    }

    private Cliente validClientExist(Long clientId) {
       return clientRepository.findById(clientId)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
