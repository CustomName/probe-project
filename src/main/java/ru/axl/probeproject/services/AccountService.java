package ru.axl.probeproject.services;

import ru.axl.probeproject.model.AccountOpenRequest;
import ru.axl.probeproject.model.AccountReserveRequest;
import ru.axl.probeproject.model.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    List<AccountResponse> findAllClientAccounts(UUID idClient);

    AccountResponse reserveAccount(AccountReserveRequest accountRequest);

    List<AccountResponse> openAccounts(AccountOpenRequest accountRequest);

}
