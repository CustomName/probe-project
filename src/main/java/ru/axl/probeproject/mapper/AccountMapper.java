package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.axl.probeproject.model.AccountResponse;
import ru.axl.probeproject.model.entities.Account;

import java.util.List;

@Mapper(uses = {UuidMapper.class})
public interface AccountMapper {

    @Mapping(target = "accountStatus", source = "accountStatus.name")
    @Mapping(target = "currencyIso", source = "currency.iso")
    @Mapping(target = "clientFio", source = "client.fio")
    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> account);

}
