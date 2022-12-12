package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

class AccountStatusRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountStatusRepository accountStatusRepo;

    @Test
    void shouldFindByName() {
        final String accountStatusName = "RESERVED";
        final AccountStatus accountStatus = accountStatusRepo.findByName(accountStatusName).orElseThrow();

        assertThat(accountStatus).isNotNull();
        assertThat(accountStatus.getName()).isEqualTo(accountStatusName);
        assertThat(accountStatus.getDescription()).isEqualTo("Счет зарезервирован");
    }

}