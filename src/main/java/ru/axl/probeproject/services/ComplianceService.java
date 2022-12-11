package ru.axl.probeproject.services;

import ru.axl.probeproject.model.entities.Client;

/**
 * Сервис для работы с комплаенс блокировками.
 */
public interface ComplianceService {

    /**
     * Метод для проверки наличия комплаенс блокировки у клиента.
     *
     * @param client Клиент
     * @return Результат проверки: true - проверка пройдена успешно, false - проверка не пройдена
     */
    boolean checkClient(Client client);

}
