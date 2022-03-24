package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.ProcessesApi;
import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.services.ProcessService;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class ProcessController implements ProcessesApi {

    private final ProcessService processService;

    @Override
    public ResponseEntity<ProcessResponse> postProcess(ProcessRequest processRequest) {
        ProcessResponse processResponse = processService.createProcess(processRequest);

        return ok(processResponse);
    }

}
