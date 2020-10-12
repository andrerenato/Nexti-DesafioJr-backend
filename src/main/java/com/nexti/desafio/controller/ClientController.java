package com.nexti.desafio.controller;


import com.nexti.desafio.model.Client;
import com.nexti.desafio.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client, HttpServletResponse response) {
        Client newClient = clientService.create(client);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(newClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Optional<Client> client = clientService.getById(id);

        return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAll();

        return !clients.isEmpty() ? ResponseEntity.ok(clients) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody @Valid Client client) throws Exception {
        Client updatedClient = clientService.update(id, client);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Boolean deletedClient = clientService.delete(id);

        return deletedClient ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
