package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ClientResponse;

import java.util.List;

public interface ClientService {

    List<ClientResponse> findAll();

    ClientResponse findByInn(String inn);

}
