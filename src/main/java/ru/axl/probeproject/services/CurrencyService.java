package ru.axl.probeproject.services;

import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.model.entities.Currency;

import java.util.List;

/**
 * Сервис для работы со справочником валют.
 */
public interface CurrencyService {

    /**
     * Метод возвращает список всех валют.
     *
     * @return Список валют
     */
    List<CurrencyResponse> getCurrencies();

    /**
     * Метод возвращает валюту по коду.
     *
     * @param code Код искомой валюты
     * @return Валюта
     */
    Currency findByCode(String code);

}
