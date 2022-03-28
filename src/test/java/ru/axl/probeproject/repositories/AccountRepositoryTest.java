package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepo;

    @Test
    @Sql(scripts = {"/data-accounts-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAllByIdClient() {

    }

}