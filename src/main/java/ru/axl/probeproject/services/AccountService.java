package ru.axl.probeproject.services;

import ru.axl.probeproject.model.AccountOpenRequest;
import ru.axl.probeproject.model.AccountReserveRequest;
import ru.axl.probeproject.model.AccountResponse;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы со счетами клиента.
 */
public interface AccountService {

    /**
     * Метод возвращает все счета клиента.
     *
     * @param idClient Идентификатор клиента
     * @return Список счетов клиента
     */
    List<AccountResponse> findAllClientAccounts(UUID idClient);

    /**
     * Метод резервирования счета.
     *
     * @param accountRequest Запрос для резервирования счета
     * @return Зарезервированный счет
     */
    AccountResponse reserveAccount(AccountReserveRequest accountRequest);

    /**
     * Метод открытия зарезервированных счетов.
     *
     * @param accountRequest Запрос для открытия счетов
     * @return Список счетов отправленных на открытие
     */
    List<AccountResponse> openAccounts(AccountOpenRequest accountRequest);

}
