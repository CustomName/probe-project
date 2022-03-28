package ru.axl.probeproject.services;

import ru.axl.probeproject.model.CurrencyResponse;

import java.util.List;

public interface CurrencyService {

    List<CurrencyResponse> getCurrencies();

}
