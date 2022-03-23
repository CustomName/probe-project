package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ClientsApi;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.services.ClientService;

@Slf4j
@RestController
@AllArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<ClientResponse> findClientByInn(String inn) {
        ClientResponse clientResponse = clientService.findByInn(inn);

        return ResponseEntity.ok(clientResponse);
    }

}
