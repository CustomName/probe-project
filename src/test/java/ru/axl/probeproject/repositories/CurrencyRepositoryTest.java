package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.axl.probeproject.model.entities.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepo;

    @Test
    void shouldFindByCode() {
        Currency currency = currencyRepo.findByCode("810").orElseThrow();
        assertThat(currency).isNotNull();
        assertThat(currency.getCode()).isEqualTo("810");
        assertThat(currency.getIso()).isEqualTo("RUB");
        assertThat(currency.getName()).isEqualTo("Российский рубль");
    }

}