package ru.axl.probeproject.services;

import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.model.entities.Currency;

import java.util.List;

public interface CurrencyService {

    List<CurrencyResponse> getCurrencies();

    Currency findByCode(String code);

}
