package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.axl.probeproject.model.entities.Account;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"/scripts/data-accounts-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AccountRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Test
    void shouldFindAllByIdClient() {
        Client client = clientRepo.findByInn("111111111111").orElseThrow();

        List<Account> accounts = accountRepo.findAllByIdClient(client.getIdClient());

        assertThat(accounts).hasSize(3);
    }

}