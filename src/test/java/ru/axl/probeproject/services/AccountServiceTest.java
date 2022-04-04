package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.AccountServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.axl.probeproject.model.enums.AccountStatusEnum.RESERVED;

class AccountServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private ProcessService processService;
    
    @Mock
    private AccountRepository accountRepo;

    @Mock
    private ClientRepository clientRepo;

    @Mock
    private AccountStatusRepository accountStatusRepo;

    @Mock
    private CurrencyRepository currencyRepo;

    @Mock
    private AccountMapper accountMapper;

    private final UUID uuidClient = UUID.randomUUID();
    private final UUID uuidCurrency = UUID.randomUUID();
    private final UUID uuidAccountStatus = UUID.randomUUID();
    private final UUID uuidAccountResponse1 = UUID.randomUUID();
    private final UUID uuidAccountResponse2 = UUID.randomUUID();
    private final UUID uuidAccount1 = UUID.randomUUID();
    private final UUID uuidAccount2 = UUID.randomUUID();
    private final String currencyCode = "810";

    @Test
    void shouldFindAllClientAccounts() {
        List<Account> accountList = getAccountList();
        List<AccountResponse> accountResponseList = getAccountResponseList();
        when(accountMapper.toAccountResponseList(accountList)).thenReturn(accountResponseList);
        when(accountRepo.findAllByIdClient(uuidClient)).thenReturn(accountList);

        List<AccountResponse> allClientAccounts = accountService.findAllClientAccounts(uuidClient);

        assertThat(allClientAccounts).hasSize(2);
    }

    @Test
    void shouldCreateAccount() {
        final Account account = getAccount(uuidAccount1);
        when(clientRepo.findByIdClient(uuidClient)).thenReturn(Optional.of(getClient()));
        when(accountStatusRepo.findByName(RESERVED.name())).thenReturn(Optional.of(getAccountStatus()));
        when(currencyRepo.findByCode(currencyCode)).thenReturn(Optional.of(getCurrency()));
        when(accountRepo.save(any())).thenReturn(account);
        when(accountMapper.toAccountResponse(account)).thenReturn(getAccountResponse(uuidAccountResponse1));

        AccountRequest accountRequest = new AccountRequest()
                .idClient(uuidClient.toString())
                .code(currencyCode);
        AccountResponse accountResponse = accountService.reserveAccount(accountRequest);

        assertThat(accountResponse).isNotNull();
    }

    private AccountResponse getAccountResponse(UUID idAccountResponse){
        return new AccountResponse().idAccount(idAccountResponse.toString());
    }

    private List<AccountResponse> getAccountResponseList(){
        return List.of(getAccountResponse(uuidAccountResponse1),
                getAccountResponse(uuidAccountResponse2));
    }

    private Client getClient(){
        return Client.builder()
                .idClient(uuidClient)
                .build();
    }

    private Currency getCurrency(){
        return Currency.builder()
                .idCurrency(uuidCurrency)
                .code(currencyCode)
                .iso("RUR")
                .name("Российский рубль")
                .build();
    }

    private AccountStatus getAccountStatus(){
        return AccountStatus.builder()
                .idAccountStatus(uuidAccountStatus)
                .name("RESERVING")
                .build();
    }

    private Account getAccount(UUID idAccount){
        Client client = getClient();
        Currency currency = getCurrency();
        AccountStatus accountStatus = getAccountStatus();

        return Account.builder()
                .idAccount(idAccount)
                .client(client)
                .currency(currency)
                .accountStatus(accountStatus)
                .build();
    }

    private List<Account> getAccountList(){
        return List.of(getAccount(uuidAccount1),
                getAccount(uuidAccount2));
    }
    
}