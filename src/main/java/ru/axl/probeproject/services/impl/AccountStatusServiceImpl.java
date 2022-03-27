package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.mapper.AccountStatusMapper;
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.repositories.AccountStatusRepository;
import ru.axl.probeproject.services.AccountStatusService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AccountStatusServiceImpl implements AccountStatusService {

    private final AccountStatusRepository accountStatusRepo;
    private final AccountStatusMapper accountStatusMapper;

    @Override
    public List<AccountStatusResponse> getAllAccountStatuses() {
        log.info("Получение всех статусов счета");
        List<AccountStatus> accountStatuses = accountStatusRepo.findAll();

        List<AccountStatusResponse> accountStatusResponses = accountStatusMapper.toAccountStatusResponseList(accountStatuses);
        log.info("Найдены статусы счета\n {}", accountStatusResponses);

        return accountStatusResponses;
    }

}
