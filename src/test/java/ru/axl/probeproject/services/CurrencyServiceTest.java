package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.axl.probeproject.mapper.CurrencyMapper;
import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.model.entities.Currency;
import ru.axl.probeproject.repositories.CurrencyRepository;
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.CurrencyServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CurrencyServiceTest extends BaseServiceTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private CurrencyRepository currencyRepo;

    @Mock
    private CurrencyMapper currencyMapper;

    private final UUID uuid1 = UUID.randomUUID();
    private final String code1 = "810";
    private final String iso1 = "RUR";
    private final String name1 = "Российский рубль";
    private final UUID uuid2 = UUID.randomUUID();
    private final String code2 = "840";
    private final String iso2 = "USD";
    private final String name2 = "Доллар США";

    @Test
    void shouldGetCurrencies() {
        final List<Currency> currencies = getCurrencies();
        final List<CurrencyResponse> currencyResponses = getCurrencyResponses();
        when(currencyRepo.findAll()).thenReturn(currencies);
        when(currencyMapper.toCurrencyResponseList(currencies)).thenReturn(currencyResponses);

        final List<CurrencyResponse> allCurrencies = currencyService.getCurrencies();

        assertThat(allCurrencies).hasSize(2);
        assertThat(allCurrencies).containsAll(currencyResponses);
    }

    private List<Currency> getCurrencies() {
        return List.of(
                Currency.builder()
                        .idCurrency(uuid1)
                        .code(code1)
                        .iso(iso1)
                        .name(name1)
                        .build(),
                Currency.builder()
                        .idCurrency(uuid2)
                        .code(code2)
                        .iso(iso2)
                        .name(name2)
                        .build()
        );
    }

    private List<CurrencyResponse> getCurrencyResponses() {
        return List.of(
                new CurrencyResponse()
                        .idCurrency(uuid1.toString())
                        .code(code1)
                        .iso(iso1)
                        .name(name1),
                new CurrencyResponse()
                        .idCurrency(uuid2.toString())
                        .code(code2)
                        .iso(iso2)
                        .name(name2)
        );
    }

}