package ru.axl.probeproject.services;

import ru.axl.probeproject.model.ClientResponse;

public interface ClientService {

    ClientResponse findByInn(String inn);

}
