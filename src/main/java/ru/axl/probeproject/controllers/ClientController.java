package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ClientsApi;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.services.ClientService;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class ClientController implements ClientsApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<ClientResponse> clientByInn(String inn) {
        Optional<Client> clientOpt = clientService.findByInn(inn);
        if (clientOpt.isEmpty()){
            log.error("не найден клиент с инн {}", inn);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Client client = clientOpt.get();
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.id(client.getId().toString());
        clientResponse.fio(client.getFio());
        clientResponse.inn(client.getInn());

        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

}
