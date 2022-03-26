package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ProcessStatusResponse;

import java.util.List;

public interface ProcessStatusService {

    List<ProcessStatusResponse> getAllProcessStatuses();

}
