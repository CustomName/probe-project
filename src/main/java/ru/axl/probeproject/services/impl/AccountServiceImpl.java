package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.exceptions.ApiException;
import ru.axl.probeproject.mapper.AccountMapper;
import ru.axl.probeproject.model.AccountRequest;
import ru.axl.probeproject.model.AccountResponse;
import ru.axl.probeproject.model.entities.Account;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Currency;
import ru.axl.probeproject.repositories.AccountRepository;
import ru.axl.probeproject.repositories.AccountStatusRepository;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.repositories.CurrencyRepository;
import ru.axl.probeproject.services.AccountService;

import java.util.List;
import java.util.UUID;

import static ru.axl.probeproject.exceptions.ApiError.*;
import static ru.axl.probeproject.model.enums.AccountStatusEnum.RESERVING;
import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;
    private final ClientRepository clientRepo;
    private final AccountStatusRepository accountStatusRepo;
    private final CurrencyRepository currencyRepo;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> findAllClientAccounts(UUID idClient) {
        log.info("Поиск всех счетов клиента с uuid = {}", idClient);
        List<Account> accounts = accountRepo.findAllByIdClient(idClient);

        List<AccountResponse> accountResponses = accountMapper.toAccountResponseList(accounts);
        log.info("Найдены счета\n {}", accountResponses);

        return accountResponses;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        log.info("Резервирование счета:\n {}", accountRequest);
        UUID idClient = UUID.fromString(accountRequest.getIdClient());
        log.info("Поиск клиента по idClient = {}", idClient);
        Client client = clientRepo.findByIdClient(idClient).orElseThrow(() ->
                new ApiException(CLIENT_NOT_FOUND, String.format("Не найден клиент с idClient = \"%s\"", idClient)));

        AccountStatus accountStatusReserve = accountStatusRepo.findByName(RESERVING.name()).orElseThrow(() ->
                new ApiException(ACCOUNT_STATUS_NOT_FOUND,
                String.format("Не найден статус счета с name = \"%s\"", RESERVING.name())));

        Currency currency = currencyRepo.findByCode(accountRequest.getCode()).orElseThrow(() ->
                new ApiException(CURRENCY_NOT_FOUND,
                        String.format("Не найден код валюты с code = \"%s\"", accountRequest.getCode())));

        Account account = new Account();
        account.setNumber(generateReserveAccountNumber(currency));
        account.setCurrency(currency);
        account.setClient(client);
        account.setReservationDate(getNowOffsetDateTime());
        account.setAccountStatus(accountStatusReserve);

        Account savedAccount = accountRepo.save(account);
        AccountResponse accountResponse = accountMapper.toAccountResponse(savedAccount);
        log.info("Счет отправлен на резервирование:\n {}", accountResponse);

        return accountResponse;
    }

    private String generateReserveAccountNumber(Currency currency){
        return String.format("40702%sXXXXXXXXXXXX", currency.getCode());
    }

}
