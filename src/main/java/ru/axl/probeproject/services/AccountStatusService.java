package ru.axl.probeproject.services;

import ru.axl.probeproject.model.AccountStatusResponse;

import java.util.List;

public interface AccountStatusService {

    List<AccountStatusResponse> getAllAccountStatuses();

}
