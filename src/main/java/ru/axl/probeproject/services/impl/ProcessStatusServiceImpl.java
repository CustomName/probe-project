package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.mapper.ProcessStatusMapper;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.ProcessStatusRepository;
import ru.axl.probeproject.services.ProcessStatusService;

import java.util.List;

import static ru.axl.probeproject.exceptions.ApiError.PROCESS_STATUS_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessStatusServiceImpl implements ProcessStatusService {

    private final ProcessStatusRepository processStatusRepo;
    private final ProcessStatusMapper processStatusMapper;

    @Override
    @Cacheable("processStatusesCache")
    public List<ProcessStatusResponse> getAllProcessStatuses() {
        log.info("Получение всех статусов процесса");
        List<ProcessStatus> processStatuses = processStatusRepo.findAll();

        List<ProcessStatusResponse> processStatusResponses = processStatusMapper.toProcessStatusResponseList(processStatuses);
        log.info("Найдены статусы процесса\n {}", processStatusResponses);

        return processStatusResponses;
    }

    @Override
    @Cacheable(value = "processStatus", key = "#name")
    public ProcessStatus findByName(String name) {
        return processStatusRepo.findByName(name).orElseThrow(() ->
                new ApiException(PROCESS_STATUS_NOT_FOUND,
                        String.format("Не найден статус процесса с name = '%s'", name)));
    }

}
