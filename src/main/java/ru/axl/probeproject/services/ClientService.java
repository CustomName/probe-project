package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ClientResponse;

import java.util.List;

/**
 * Сервис для работы с клиентами
 */
public interface ClientService {

    /**
     * Метод возвращает список всех клиентов
     *
     * @return Список клиентов
     */
    List<ClientResponse> findAll();

    /**
     * Метод возвращает клиента по ИНН
     *
     * @param inn ИНН искомого клиента
     * @return Клиент
     */
    ClientResponse findByInn(String inn);

}
