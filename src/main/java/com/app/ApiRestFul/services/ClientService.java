package com.app.ApiRestFul.services;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.ClientRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<Client> getAllClient() throws NullPointerException {
        List<Client> clientes = repository.findAll();
        if (!clientes.isEmpty()) {
            return clientes;
        } else {
            throw new NullPointerException("Valor nulo");
        }

    }

    public Client getClientById(String id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id != null) {
            try {
                Optional<Client> client = repository.findById(id);
                if (client.isPresent()) {
                    return client.get();
                } else {
                    throw new RecordNotFoundException("No client record exist for given id", id);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Valor nulo");
        }

    }

    public Client createClient(Client cliente) {
        cliente = repository.save(cliente);
        return cliente;
    }

    public Client UpdateClient(Client entity) {
        if (entity.getId() != null) {
            Optional<Client> cliente = repository.findById(entity.getId());

            if (cliente.isPresent()) {
                Client newClient = cliente.get();
                newClient.setEmail(entity.getEmail());
                newClient.setName(entity.getName());
                newClient.setPhone(entity.getPhone());
                newClient.setSurname(entity.getSurname());
                newClient.setAdmin(entity.getAdmin());
                newClient.setType(entity.isType());
                newClient=repository.save(newClient);
                return newClient;
            } else {
                throw new RecordNotFoundException("Client not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("Client not found", entity.getId());
        }

    }

    public void deleteClient(String id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id != null) {
            try {
                Optional<Client> cliente = repository.findById(id);
                if (cliente.isPresent()) {
                    repository.deleteById(id);
                } else {
                    throw new RecordNotFoundException("No client record exist for given id", id);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }

        } else {
            throw new NullPointerException("Valor nulo");
        }

    }
}
