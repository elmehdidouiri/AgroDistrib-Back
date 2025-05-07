package org.example.miniprojetback.Services.impl;

import jakarta.transaction.Transactional;
import org.example.miniprojetback.DAOs.request.ClientRequest;
import org.example.miniprojetback.DAOs.response.ClientResponse;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.example.miniprojetback.Models.Client;
import org.example.miniprojetback.Repositories.ClientRepository;
import org.example.miniprojetback.Services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.miniprojetback.Models.enums.Role.CLIENT;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        client.setTelephone(clientRequest.getTelephone());
        client.setPassword(passwordEncoder.encode(clientRequest.getPassword()));
        client.setAdresse(clientRequest.getAdresse());
        client.setPoints(clientRequest.getPoints()+1);
        client.setCreatedAt(LocalDateTime.now());
        client.setRole(CLIENT);
        client.setActive(true);
        client = clientRepository.save(client);
        return mapToClientResponse(client);
    }
    @Transactional
    @Override
    public ClientResponse getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
        return mapToClientResponse(client);
    }
    @Transactional
    @Override
    public List<ClientResponse> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::mapToClientResponse).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public ClientResponse updateClient(Long id, ClientRequest clientRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        client.setTelephone(clientRequest.getTelephone());
        client.setAdresse(clientRequest.getAdresse());
        client.setPoints(clientRequest.getPoints());
        client.setActive(clientRequest.isActive());
        client = clientRepository.save(client);

        return mapToClientResponse(client);
    }
    @Transactional
    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
        clientRepository.delete(client);
    }

    private ClientResponse mapToClientResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setName(client.getName());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setTelephone(client.getTelephone());
        clientResponse.setAdresse(client.getAdresse());
        clientResponse.setPoints(client.getPoints());
        clientResponse.setActive(client.isActive());
        return clientResponse;
    }
}
