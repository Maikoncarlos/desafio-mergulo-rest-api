package com.github.maikoncarlos.desafio_mergulho_rest_api.controller;

import com.github.maikoncarlos.desafio_mergulho_rest_api.domain.model.Cliente;
import com.github.maikoncarlos.desafio_mergulho_rest_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientList() {
        return ResponseEntity.ok().body(clientRepository.findByNameContains("maik"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> searchClient(@PathVariable("id") Long clientId) {
        return ResponseEntity.ok().body(getCliente(clientId));
    }

    @PostMapping()
    public ResponseEntity<Cliente> createdClient(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable("id") Long clientId , @RequestBody Cliente cliente){
        getCliente(clientId);
        cliente.setId(clientId);
        return ResponseEntity.ok().body(clientRepository.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long clientId) {
        getCliente(clientId);
        clientRepository.deleteById(clientId);
        return ResponseEntity.noContent().build();
    }

    private Cliente getCliente(Long clientId) {
       return clientRepository.findById(clientId)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
