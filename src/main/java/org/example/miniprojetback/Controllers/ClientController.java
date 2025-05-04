package org.example.miniprojetback.Controllers;

import org.example.miniprojetback.DAOs.request.ClientRequest;
import org.example.miniprojetback.DAOs.response.ClientResponse;
import org.example.miniprojetback.Services.IClientService;
import org.example.miniprojetback.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.createClient(clientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        try {
            ClientResponse clientResponse = clientService.getClient(id);
            return ResponseEntity.ok(clientResponse);
        } catch (ResourceNotFoundException e) {
            throw e; // Cette exception sera capturée par @ControllerAdvice
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clientResponses = clientService.getAllClients();
        return ResponseEntity.ok(clientResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        try {
            ClientResponse clientResponse = clientService.updateClient(id, clientRequest);
            return ResponseEntity.ok(clientResponse);
        } catch (ResourceNotFoundException e) {
            throw e; // Cette exception sera capturée par @ControllerAdvice
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw e; // Cette exception sera capturée par @ControllerAdvice
        }
    }
}
