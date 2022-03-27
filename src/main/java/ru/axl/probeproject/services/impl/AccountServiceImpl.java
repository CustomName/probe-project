package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.mapper.AccountMapper;
import ru.axl.probeproject.model.AccountResponse;
import ru.axl.probeproject.model.entities.Account;
import ru.axl.probeproject.repositories.AccountRepository;
import ru.axl.probeproject.services.AccountService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> findAllClientAccounts(UUID idClient) {
        log.info("Поиск всех счетов клиента с uuid = {}", idClient);
        List<Account> accounts = accountRepo.findAllByIdClient(idClient);

        List<AccountResponse> accountResponses = accountMapper.toAccountResponseList(accounts);
        log.info("Найдены счета\n {}", accountResponses);

        return accountResponses;
    }

}
