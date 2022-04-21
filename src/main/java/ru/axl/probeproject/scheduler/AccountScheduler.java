package ru.axl.probeproject.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.axl.probeproject.model.entities.Account;
import ru.axl.probeproject.model.entities.AccountStatus;
import ru.axl.probeproject.model.entities.Currency;
import ru.axl.probeproject.model.enums.AccountStatusEnum;
import ru.axl.probeproject.repositories.AccountRepository;
import ru.axl.probeproject.services.AccountStatusService;
import ru.axl.probeproject.services.ProcessService;

import javax.transaction.Transactional;
import java.util.List;

import static ru.axl.probeproject.model.enums.AccountStatusEnum.*;
import static ru.axl.probeproject.model.enums.ProcessStatusEnum.ACCOUNT_PROCESSING;
import static ru.axl.probeproject.model.enums.ProcessStatusEnum.DONE;
import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;
import static ru.axl.probeproject.utils.Utils.randNumberWithBounds;

@Slf4j
@Component
@AllArgsConstructor
public class AccountScheduler {

    private final ProcessService processService;
    private final AccountRepository accountRepo;
    private final AccountStatusService accountStatusService;

    @Transactional
    @Scheduled(fixedDelayString = "${scheduler-config.fixed-delay}",
            initialDelayString = "${scheduler-config.initial-delay}")
    public void scheduleAccountsOpen(){
        List<Account> accounts = accountRepo.findAllInStatusOpeningAndOpeningDateBefore(OPENING.name(),
                getNowOffsetDateTime().minusSeconds(10));
        if(accounts.isEmpty()) return;
        AccountStatus opened = getStatus(OPENED);
        AccountStatus rejected = getStatus(REJECTED);

        accounts.stream()
            .map(Account::getClient)
            .distinct()
            .forEach(cl -> {
                processService.changeProcessStatusByClient(cl, ACCOUNT_PROCESSING, DONE);
            });

        accounts.forEach(acc -> {
            acc.setNumber(generateAccountNumber(acc.getCurrency()));
            acc.setAccountStatus(acc.getCurrency().getCode().equals("810") ? opened : rejected);
            acc.setOpenDate(acc.getAccountStatus().equals(rejected) ? getNowOffsetDateTime() : null);
        });

        log.info("Результат открытия счетов\n {}", accounts);
    }

    private AccountStatus getStatus(AccountStatusEnum accountStatusEnum){
        return accountStatusService.findByName(accountStatusEnum.name());
    }

    private String generateAccountNumber(Currency currency){
        return String.format("40702%s000000%d", currency.getCode(), randNumberWithBounds(111111, 999999));
    }

}
