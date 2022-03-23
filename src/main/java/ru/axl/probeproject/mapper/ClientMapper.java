package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.model.entities.Client;

@Mapper(uses = {UuidMapper.class})
public interface ClientMapper {

    ClientResponse toClientResponse(Client client);

}
