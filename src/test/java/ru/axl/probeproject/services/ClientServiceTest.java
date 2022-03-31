package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.axl.probeproject.mapper.ClientMapper;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.ClientServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ClientServiceTest extends BaseServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepo;

    @Mock
    private ClientMapper clientMapper;

    private final UUID uuidClient1 = UUID.randomUUID();
    private final UUID uuidClient2 = UUID.randomUUID();
    private final String clientInn = "111111111111";
    private final String clientFio = "Петров Петр Иванович";

    @Test
    void shouldFindAll() {
        List<Client> clients = List.of(getClient(uuidClient1), getClient(uuidClient2));
        List<ClientResponse> clientResponses = List.of(getClientResponse(uuidClient1.toString()),
                getClientResponse(uuidClient2.toString()));
        when(clientRepo.findAll()).thenReturn(clients);
        when(clientMapper.toClientResponseList(clients)).thenReturn(clientResponses);

        List<ClientResponse> responses = clientService.findAll();

        assertThat(responses).hasSize(2);
    }

    @Test
    void shouldFindByInn() {
        final Client client = getClient(uuidClient1);
        final ClientResponse clientResponse = getClientResponse(uuidClient1.toString());
        when(clientRepo.findByInn(clientInn)).thenReturn(Optional.of(client));
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        ClientResponse foundClient = clientService.findByInn(clientInn);

        assertThat(foundClient).isNotNull();
        assertThat(foundClient).isEqualTo(clientResponse);
    }

    private Client getClient(UUID uuidClient){
        return Client.builder()
                .idClient(uuidClient)
                .build();
    }

    private ClientResponse getClientResponse(String uuidClient){
        return new ClientResponse()
                .idClient(uuidClient)
                .fio(clientFio)
                .inn(clientInn);
    }

}