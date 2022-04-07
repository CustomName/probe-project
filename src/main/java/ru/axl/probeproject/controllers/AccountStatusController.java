package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.AccountStatusesApi;
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.services.AccountStatusService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountStatusController implements AccountStatusesApi {

    private final AccountStatusService accountStatusService;

    @Override
    public ResponseEntity<List<AccountStatusResponse>> getAllAccountStatuses() {
        List<AccountStatusResponse> accountStatuses = accountStatusService.getAllAccountStatuses();

        return ok(accountStatuses);
    }

}
