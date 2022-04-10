package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.axl.probeproject.model.enums.ProcessStatusEnum.NEW;

@Sql(scripts = {"/scripts/data-processes-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProcessRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProcessRepository processRepo;

    @Autowired
    private ProcessStatusRepository processStatusRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Test
    void shouldFindAllByIdClient() {
        Client client = clientRepo.findByInn("111111111111").orElseThrow();

        List<Process> processes = processRepo.findAllByIdClient(client.getIdClient());

        assertThat(processes).hasSize(3);
    }

    @Test
    void shouldFindAllByIdClientInStatuses() {
        Client client = clientRepo.findByInn("111111111112").orElseThrow();

        List<Process> processes = processRepo.findAllByIdClientInStatuses(client.getIdClient(),
                Set.of("NEW", "COMPLIANCE_SUCCESS", "ACCOUNT_PROCESSING"));
        assertThat(processes).hasSize(0);

        processes = processRepo.findAllByIdClientInStatuses(client.getIdClient(),
                Set.of("COMPLIANCE_ERROR", "DONE"));
        assertThat(processes).hasSize(2);
    }

    @Test
    void shouldFindAllWithStatusAndLastUpdateDateBefore() {
        ProcessStatus newStatus = processStatusRepo.findByName(NEW.name()).orElseThrow();

        List<Process> processes = processRepo.findAllWithStatusAndLastUpdateDateBefore(newStatus,
                OffsetDateTime.now().minusMinutes(5));
        assertThat(processes).hasSize(0);

        processes = processRepo.findAllWithStatusAndLastUpdateDateBefore(newStatus,
                OffsetDateTime.now().plusMinutes(5));
        assertThat(processes).hasSize(3);
    }

}