package ru.axl.probeproject.services;

import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;

import java.util.List;

/**
 * Сервис для доступа к статусам счета.
 */
public interface AccountStatusService {

    /**
     * Метод возвращает все возможные статусы счета.
     *
     * @return Список статусов счета
     */
    List<AccountStatusResponse> getAllAccountStatuses();

    /**
     * Метод возвращает статус счета по его имени.
     *
     * @param name Имя статуса счета
     * @return Статус счета
     */
    AccountStatus findByName(String name);

}
