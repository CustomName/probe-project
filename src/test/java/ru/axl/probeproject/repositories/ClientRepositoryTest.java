package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ClientRepository clientRepo;

    @Test
    void shouldFindByInn() {
        final String clientInn = "111111111111";
        final Client client = clientRepo.findByInn(clientInn).orElseThrow();

        assertThat(client).isNotNull();
        assertThat(client.getInn()).isEqualTo(clientInn);
        assertThat(client.getFio()).isEqualTo("Пупов Пуп Пупович");
    }

    @Test
    void shouldFindByIdClient() {
        Client client = new Client();
        client.setInn("000000000000");
        client.setFio("Тестовый Тест Тестович");
        client = clientRepo.save(client);

        final Client foundClient = clientRepo.findByIdClient(client.getIdClient()).orElseThrow();
        assertThat(foundClient).isNotNull();
        assertThat(foundClient).isEqualTo(client);
    }

}