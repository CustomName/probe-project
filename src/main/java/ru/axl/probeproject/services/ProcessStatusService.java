package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;

import java.util.List;

public interface ProcessStatusService {

    List<ProcessStatusResponse> getAllProcessStatuses();

    ProcessStatus findByName(String name);

}
