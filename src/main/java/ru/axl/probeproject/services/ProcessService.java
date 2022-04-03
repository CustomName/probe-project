package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.enums.ProcessStatusEnum;

import java.util.List;
import java.util.UUID;

public interface ProcessService {

    ProcessResponse createProcess(ProcessRequest processRequest);

    List<ProcessResponse> findAllClientProcesses(UUID idClient);

    void changeProcessStatusByClient(Client client, ProcessStatusEnum oldProcessStatus,
                                     ProcessStatusEnum newProcessStatus);

}
