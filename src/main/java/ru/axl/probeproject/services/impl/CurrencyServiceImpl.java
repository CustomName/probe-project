package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.mapper.CurrencyMapper;
import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.model.entities.Currency;
import ru.axl.probeproject.repositories.CurrencyRepository;
import ru.axl.probeproject.services.CurrencyService;

import java.util.List;

import static ru.axl.probeproject.exceptions.ApiError.CURRENCY_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepo;
    private final CurrencyMapper currencyMapper;

    @Override
    @Cacheable("currenciesCache")
    public List<CurrencyResponse> getCurrencies() {
        log.info("Получение кодов валют");
        List<Currency> currencies = currencyRepo.findAll();

        List<CurrencyResponse> currencyResponses = currencyMapper.toCurrencyResponseList(currencies);
        log.info("Найдены коды валют\n {}", currencyResponses);

        return currencyResponses;
    }

    @Override
    @Cacheable(value = "currencyCache", key = "#code")
    public Currency findByCode(String code) {
        return currencyRepo.findByCode(code).orElseThrow(() ->
                new ApiException(CURRENCY_NOT_FOUND,
                        String.format("Не найден код валюты с code = \"%s\"", code)));
    }

}
