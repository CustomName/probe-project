package ru.axl.probeproject.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.services.ClientService;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Optional<Client> findByInn(String inn) {
        log.info("Поиск клиента по инн {}", inn);
        return clientRepository.findByInn(inn);
    }

}
