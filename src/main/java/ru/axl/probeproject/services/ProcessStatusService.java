package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;

import java.util.List;

/**
 * Сервис для доступа к статусам процесса.
 */
public interface ProcessStatusService {

    /**
     * Метод возвращает все возможные статусы процесса.
     *
     * @return Список статусов процесса
     */
    List<ProcessStatusResponse> getAllProcessStatuses();

    /**
     * Метод возвращает статус процесса по его имени.
     *
     * @param name Имя статуса процесса
     * @return Статус процесса
     */
    ProcessStatus findByName(String name);

}
