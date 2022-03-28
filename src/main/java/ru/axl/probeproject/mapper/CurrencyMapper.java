package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.model.entities.Currency;

import java.util.List;

@Mapper(uses = {UuidMapper.class})
public interface CurrencyMapper {

    CurrencyResponse toCurrencyResponse(Currency currency);

    List<CurrencyResponse> toCurrencyResponseList(List<Currency> currency);

}