package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ClientsApi;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.services.ClientService;

import java.util.Optional;

import static ru.axl.probeproject.exceptions.ApiError.CLIENT_NOT_FOUND;

@Slf4j
@RestController
@AllArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<ClientResponse> findClientByInn(String inn) {
        Optional<Client> clientOpt = clientService.findByInn(inn);

        Client client = clientOpt.orElseThrow(() ->
                new ApiException(CLIENT_NOT_FOUND, String.format("Не найден клиент с инн %s", inn)));
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.id(client.getId().toString());
        clientResponse.fio(client.getFio());
        clientResponse.inn(client.getInn());

        return ResponseEntity.ok(clientResponse);
    }

}
