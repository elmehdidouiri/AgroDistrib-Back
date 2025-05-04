package org.example.miniprojetback.Services;

import org.example.miniprojetback.DAOs.request.ClientRequest;
import org.example.miniprojetback.DAOs.response.ClientResponse;

import java.util.List;

public interface IClientService {

    ClientResponse createClient(ClientRequest clientRequest);

    ClientResponse getClient(Long id);

    List<ClientResponse> getAllClients();

    ClientResponse updateClient(Long id, ClientRequest clientRequest);

    void deleteClient(Long id);
}
