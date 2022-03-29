package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Process;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"/scripts/data-processes-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProcessRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProcessRepository processRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Test
    void shouldFindAllByIdClient() {
        Client client = clientRepo.findByInn("111111111111").orElseThrow();

        List<Process> processes = processRepo.findAllByIdClient(client.getIdClient());

        assertThat(processes).hasSize(3);
    }

}