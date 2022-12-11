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
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.model.enums.ProcessStatusEnum;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.repositories.ProcessRepository;
import ru.axl.probeproject.services.ProcessService;
import ru.axl.probeproject.services.ProcessStatusService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static ru.axl.probeproject.exceptions.ApiError.*;
import static ru.axl.probeproject.model.enums.ProcessStatusEnum.*;
import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepo;
    private final ClientRepository clientRepo;
    private final ProcessStatusService processStatusService;
    private final ProcessMapper processMapper;

    private final Set<String> notTerminalStatuses = Set.of(
        NEW.name(), COMPLIANCE_SUCCESS.name(), COMPLIANCE_ERROR.name(), ACCOUNT_PROCESSING.name()
    );

    @Override
    @Transactional
    public ProcessResponse createProcess(final ProcessRequest processRequest) {
        log.info("Создание нового процесса для клиента с uuid = {}", processRequest.getIdClient());

        final Client client = clientRepo.findByIdClient(UUID.fromString(processRequest.getIdClient())).orElseThrow(() ->
                new ApiException(CLIENT_NOT_FOUND,
                        String.format("Не найден клиент с uuid = %s", processRequest.getIdClient())));

        checkNotTerminalProcessesByClient(client);

        final ProcessStatus processStatusNew = getProcessStatus(NEW);

        Process process = new Process();
        process.setClient(client);
        process.setStartDate(getNowOffsetDateTime());
        process.setLastUpdateDate(getNowOffsetDateTime());
        process.setProcessStatus(processStatusNew);
        process = processRepo.save(process);

        final ProcessResponse processResponse = processMapper.toProcessResponse(process);
        log.info("Процесс создан\n{}", processResponse);

        return processResponse;
    }

    @Override
    public List<ProcessResponse> findAllClientProcesses(final UUID idClient) {
        log.info("Поиск всех процессов клиента с uuid = {}", idClient);
        final List<Process> processes = processRepo.findAllByIdClient(idClient);

        final List<ProcessResponse> processResponses = processMapper.toProcessResponseList(processes);
        log.info("Найдены процессы\n{}", processResponses);

        return processResponses;
    }

    @Override
    @Transactional
    public void changeProcessStatusByClient(final Client client, final ProcessStatusEnum oldProcessStatus,
                                            final ProcessStatusEnum newProcessStatus) {
        final ProcessStatus processStatus = getProcessStatus(newProcessStatus);

        final Process activeProcess = checkAndGetProcessActiveStatus(client, oldProcessStatus);

        activeProcess.setLastUpdateDate(getNowOffsetDateTime());
        activeProcess.setProcessStatus(processStatus);
        processRepo.save(activeProcess);
    }

    @Override
    public Process checkAndGetProcessActiveStatus(final Client client, final ProcessStatusEnum processStatus) {
        final List<Process> processes = processRepo.findAllByIdClient(client.getIdClient());

        final List<Process> activeProcesses = processes.stream()
                .filter(process -> process.getProcessStatus().getName().equals(processStatus.name()))
                .collect(toList());

        if (activeProcesses.size() == 0) {
            throw new ApiException(PROCESS_NOT_FOUND, "У клиента нет активного процесса");
        }

        if (activeProcesses.size() > 1) {
            throw new ApiException(PROCESS_TOO_MUCH,
                    String.format("У клиента более 1 активного процесса:\n%s", activeProcesses));
        }

        return activeProcesses.get(0);
    }

    private ProcessStatus getProcessStatus(final ProcessStatusEnum newProcessStatus) {
        return processStatusService.findByName(newProcessStatus.name());
    }

    private void checkNotTerminalProcessesByClient(final Client client) {
        final List<Process> notTerminalProcesses = processRepo.findAllByIdClientInStatuses(client.getIdClient(),
                notTerminalStatuses);
        if (!notTerminalProcesses.isEmpty()) {
            throw new ApiException(CLIENT_HAS_NOT_TERMINATED_PROCESSES,
                    String.format("У клиента есть незавершенные процессы:\n%s", notTerminalProcesses));
        }
    }

}
