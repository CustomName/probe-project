package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.mapper.ProcessStatusMapper;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.ProcessStatusRepository;
import ru.axl.probeproject.services.ProcessStatusService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessStatusServiceImpl implements ProcessStatusService {

    private final ProcessStatusRepository processStatusRepo;
    private final ProcessStatusMapper processStatusMapper;

    @Override
    public List<ProcessStatusResponse> getAllProcessStatuses() {
        log.info("Получение всех статусов процесса");
        List<ProcessStatus> processStatuses = processStatusRepo.findAll();

        List<ProcessStatusResponse> processStatusResponses = processStatusMapper.toProcessStatusResponseList(processStatuses);
        log.info("Найдены статусы процесса\n {}", processStatusResponses);

        return processStatusResponses;
    }

}
