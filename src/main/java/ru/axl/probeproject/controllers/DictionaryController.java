package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.DictionariesApi;
import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.services.CurrencyService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class DictionaryController implements DictionariesApi {

    private final CurrencyService currencyService;

    @Override
    public ResponseEntity<List<CurrencyResponse>> getCurrencies() {
        final List<CurrencyResponse> currencies = currencyService.getCurrencies();

        return ok(currencies);
    }

}
