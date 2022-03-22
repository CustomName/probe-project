package ru.axl.probeproject.services;

import ru.axl.probeproject.model.entities.Client;

import java.util.Optional;

public interface ClientService {

    Optional<Client> findByInn(String inn);

}
