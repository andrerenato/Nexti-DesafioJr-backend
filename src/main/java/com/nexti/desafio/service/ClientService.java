package com.nexti.desafio.service;

import com.nexti.desafio.model.Client;
import com.nexti.desafio.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Client update(Long id, Client client) {
        Optional<Client> savedClient = clientRepository.findById(id);
        Client updatedClient;
        if(savedClient.isPresent()){
            updatedClient = savedClient.get();
            BeanUtils.copyProperties(client, updatedClient, "id");
            clientRepository.save(updatedClient);
        }else{
            return null;
        }
        return updatedClient;
    }

    public Boolean delete(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            clientRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
