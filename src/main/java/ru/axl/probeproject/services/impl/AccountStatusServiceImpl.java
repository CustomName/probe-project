package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.mapper.AccountStatusMapper;
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.repositories.AccountStatusRepository;
import ru.axl.probeproject.services.AccountStatusService;

import java.util.List;

import static ru.axl.probeproject.exceptions.ApiError.ACCOUNT_STATUS_NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class AccountStatusServiceImpl implements AccountStatusService {

    private final AccountStatusRepository accountStatusRepo;
    private final AccountStatusMapper accountStatusMapper;

    @Override
    @Cacheable("accountStatusesCache")
    public List<AccountStatusResponse> getAllAccountStatuses() {
        log.info("Получение всех статусов счета");
        List<AccountStatus> accountStatuses = accountStatusRepo.findAll();

        List<AccountStatusResponse> accountStatusResponses = accountStatusMapper.toAccountStatusResponseList(accountStatuses);
        log.info("Найдены статусы счета\n {}", accountStatusResponses);

        return accountStatusResponses;
    }

    @Override
    @Cacheable(value = "accountStatusCache", key = "#name")
    public AccountStatus findByName(String name) {
        log.info("Получение статуса счета с name = " + name);
        AccountStatus accountStatus = accountStatusRepo.findByName(name).orElseThrow(() ->
                        new ApiException(ACCOUNT_STATUS_NOT_FOUND,
                                String.format("Не найден статус счета с name = \"%s\"", name)));

        log.info("Найден статус счета\n {}", accountStatus);

        return accountStatus;
    }

}
