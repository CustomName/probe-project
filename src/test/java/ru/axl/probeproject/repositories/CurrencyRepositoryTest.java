package ru.axl.probeproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.axl.probeproject.model.entities.Currency;
import ru.axl.probeproject.repositories.base.BaseRepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepo;

    @Test
    void shouldFindByCode() {
        final String currencyCode = "810";
        Currency currency = currencyRepo.findByCode(currencyCode).orElseThrow();

        assertThat(currency).isNotNull();
        assertThat(currency.getCode()).isEqualTo(currencyCode);
        assertThat(currency.getIso()).isEqualTo("RUB");
        assertThat(currency.getName()).isEqualTo("Российский рубль");
    }

}