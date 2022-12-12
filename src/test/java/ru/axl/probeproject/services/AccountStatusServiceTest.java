package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.axl.probeproject.mapper.AccountStatusMapper;
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.repositories.AccountStatusRepository;
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.AccountStatusServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AccountStatusServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountStatusServiceImpl accountStatusService;

    @Mock
    private AccountStatusRepository accountStatusRepo;

    @Mock
    private AccountStatusMapper accountStatusMapper;

    @Test
    void shouldGetAllAccountStatuses() {
        final AccountStatus accountStatus1 = AccountStatus.builder()
                .idAccountStatus(UUID.randomUUID())
                .name("RESERVING")
                .description("Счет на резервировании")
                .build();

        final AccountStatus accountStatus2 = AccountStatus.builder()
                .idAccountStatus(UUID.randomUUID())
                .name("OPENING")
                .description("Счет на открытии")
                .build();

        final AccountStatusResponse accountStatusResp1 = new AccountStatusResponse()
                .idAccountStatus(accountStatus1.getIdAccountStatus().toString())
                .name(accountStatus1.getName())
                .description(accountStatus1.getDescription());

        final AccountStatusResponse accountStatusResp2 = new AccountStatusResponse()
                .idAccountStatus(accountStatus2.getIdAccountStatus().toString())
                .name(accountStatus2.getName())
                .description(accountStatus2.getDescription());

        final List<AccountStatus> accountStatuses = List.of(accountStatus1, accountStatus2);
        final List<AccountStatusResponse> accountStatusResponses = List.of(accountStatusResp1, accountStatusResp2);

        when(accountStatusMapper.toAccountStatusResponseList(accountStatuses)).thenReturn(accountStatusResponses);
        when(accountStatusRepo.findAll()).thenReturn(accountStatuses);

        final List<AccountStatusResponse> allAccountStatusesResp = accountStatusService.getAllAccountStatuses();
        assertThat(allAccountStatusesResp).hasSize(2);

        assertThat(allAccountStatusesResp).containsAll(accountStatusResponses);
    }

}