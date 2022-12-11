package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ProcessStatusesApi;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.services.ProcessStatusService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class ProcessStatusController implements ProcessStatusesApi {

    private final ProcessStatusService processStatusService;

    @Override
    public ResponseEntity<List<ProcessStatusResponse>> getAllProcessStatuses() {
        final List<ProcessStatusResponse> processStatuses = processStatusService.getAllProcessStatuses();

        return ok(processStatuses);
    }
}
