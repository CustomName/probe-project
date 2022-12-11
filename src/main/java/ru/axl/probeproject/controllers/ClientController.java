package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ClientsApi;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.services.ClientService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<List<ClientResponse>> getClients() {
        final List<ClientResponse> clientResponses = clientService.findAll();

        return ok(clientResponses);
    }

    @Override
    public ResponseEntity<ClientResponse> findClientByInn(final String inn) {
        final ClientResponse clientResponse = clientService.findByInn(inn);

        return ok(clientResponse);
    }

}
