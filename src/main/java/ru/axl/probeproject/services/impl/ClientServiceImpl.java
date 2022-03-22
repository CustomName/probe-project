package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.services.ClientService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Optional<Client> findByInn(String inn) {
        return clientRepository.findByInn(inn);
    }

}
