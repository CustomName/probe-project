package ru.axl.probeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.axl.probeproject.api.AccountsApi;
import ru.axl.probeproject.model.AccountResponse;
import ru.axl.probeproject.services.AccountService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountController implements AccountsApi {

    private final AccountService accountService;

    @Override
    public ResponseEntity<List<AccountResponse>> getAllClientAccounts(String idClient) {
        List<AccountResponse> accounts = accountService.findAllClientAccounts(UUID.fromString(idClient));

        return ok(accounts);
    }

}
