package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.mapper.ProcessMapper;
import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.repositories.ProcessRepository;
import ru.axl.probeproject.services.ProcessService;

import static ru.axl.probeproject.exceptions.ApiError.CLIENT_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepo;
    private final ClientRepository clientRepo;
    private final ProcessMapper processMapper;

    @Override
    public ProcessResponse createProcess(ProcessRequest processRequest) {
        log.info("Создание нового процесса для пользователя с инн {}", processRequest.getClientInn());

        Client client = clientRepo.findByInn(processRequest.getClientInn()).orElseThrow(() ->
                new ApiException(CLIENT_NOT_FOUND, String.format("Не найден клиент с инн %s", processRequest.getClientInn())));
        Process process = new Process();
        process.setClient(client);
        process = processRepo.save(process);

        ProcessResponse processResponse = processMapper.toProcessResponse(process);
        log.info("Процесс создан {}", processResponse);

        return processResponse;
    }

}
