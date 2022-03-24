package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;

public interface ProcessService {

    ProcessResponse createProcess(ProcessRequest processRequest);

}
