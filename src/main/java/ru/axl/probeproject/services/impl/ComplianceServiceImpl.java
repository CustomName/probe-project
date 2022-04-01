package ru.axl.probeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.services.ComplianceService;

import java.util.Set;

@Slf4j
@Service
public class ComplianceServiceImpl implements ComplianceService {

    private final Set<String> badInnSets = Set.of("111111111111");

    @Override
    public boolean checkClient(Client client) {
        return !badInnSets.contains(client.getInn());
    }

}
