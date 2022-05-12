package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.model.enums.ProcessStatusEnum;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с процессами клиента
 */
public interface ProcessService {

    /**
     * Метод создает новый процесс
     *
     * @param processRequest Запрос для создания процесса
     * @return Созданный процесс
     */
    ProcessResponse createProcess(ProcessRequest processRequest);

    /**
     * Метод возвращает список всех процессов (активных и завершенных) клиента
     *
     * @param idClient Идентификатор клиента
     * @return Список процессов
     */
    List<ProcessResponse> findAllClientProcesses(UUID idClient);

    /**
     * Метод для изменения статуса процесса клиента со старого на новый
     *
     * @param client Клиент
     * @param oldProcessStatus Старый статус процесса
     * @param newProcessStatus Новый статус процесса
     */
    void changeProcessStatusByClient(Client client, ProcessStatusEnum oldProcessStatus,
                                     ProcessStatusEnum newProcessStatus);

    /**
     * Метод для проверки наличия у клиента процесса в активном статусе с возвратом процесса
     *
     * @param client Клиент
     * @param processStatus Активный статус
     * @return Процесс
     */
    Process checkAndGetProcessActiveStatus(Client client, ProcessStatusEnum processStatus);

}
