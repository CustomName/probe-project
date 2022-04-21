package ru.axl.probeproject.services;

import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;

import java.util.List;

public interface AccountStatusService {

    List<AccountStatusResponse> getAllAccountStatuses();

    AccountStatus findByName(String name);

}
