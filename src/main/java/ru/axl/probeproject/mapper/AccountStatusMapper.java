package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.model.entities.AccountStatus;

import java.util.List;

@Mapper(uses = UuidMapper.class)
public interface AccountStatusMapper {

    AccountStatusResponse toAccountStatusResponse(AccountStatus accountStatus);

    List<AccountStatusResponse> toAccountStatusResponseList(List<AccountStatus> accountStatus);

}
