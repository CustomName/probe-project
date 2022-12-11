package ru.axl.probeproject.mapper;

import org.mapstruct.Mapper;
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.model.entities.Client;

import java.util.List;

@Mapper(uses = UuidMapper.class)
public interface ClientMapper {

    ClientResponse toClientResponse(Client client);

    List<ClientResponse> toClientResponseList(List<Client> client);

}
