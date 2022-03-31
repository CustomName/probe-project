package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.axl.probeproject.mapper.ProcessMapper;
import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.model.entities.Client;
import ru.axl.probeproject.model.entities.Process;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.ClientRepository;
import ru.axl.probeproject.repositories.ProcessRepository;
import ru.axl.probeproject.repositories.ProcessStatusRepository;
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.ProcessServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProcessServiceTest extends BaseServiceTest {

    @InjectMocks
    private ProcessServiceImpl processService;

    @Mock
    private ProcessRepository processRepo;

    @Mock
    private ClientRepository clientRepo;

    @Mock
    private ProcessStatusRepository processStatusRepo;

    @Mock
    private ProcessMapper processMapper;

    private final UUID uuidClient = UUID.randomUUID();
    private final UUID uuidProcessStatus = UUID.randomUUID();
    private final UUID uuidProcess1 = UUID.randomUUID();
    private final UUID uuidProcess2 = UUID.randomUUID();

    @Test
    void createProcess() {
        Client client = getClient();
        ProcessStatus processStatus = getProcessStatus();
        Process process = getProcess(uuidProcess1);
        ProcessResponse processResponse = getProcessResponse(uuidProcess1);
        when(clientRepo.findByInn(client.getInn())).thenReturn(Optional.of(client));
        when(processStatusRepo.findByName(processStatus.getName())).thenReturn(Optional.of(processStatus));
        when(processRepo.save(any())).thenReturn(process);
        when(processMapper.toProcessResponse(process)).thenReturn(processResponse);

        ProcessRequest processRequest = new ProcessRequest()
                .clientInn(client.getInn());
        ProcessResponse resp = processService.createProcess(processRequest);

        assertThat(resp).isNotNull();
        assertThat(resp).isEqualTo(processResponse);
    }

    @Test
    void findAllClientProcesses() {
        Client client = getClient();
        List<Process> processes = List.of(getProcess(uuidProcess1), getProcess(uuidProcess2));
        List<ProcessResponse> processResponses = List.of(getProcessResponse(uuidProcess1), getProcessResponse(uuidProcess2));
        when(processRepo.findAllByIdClient(client.getIdClient())).thenReturn(processes);
        when(processMapper.toProcessResponseList(processes)).thenReturn(processResponses);

        List<ProcessResponse> resp = processService.findAllClientProcesses(client.getIdClient());

        assertThat(resp).hasSize(2);
        assertThat(resp).containsAll(processResponses);
    }

    private Client getClient(){
        return Client.builder()
                .idClient(uuidClient)
                .inn("111111111111")
                .fio("Иванов Иван Иванович")
                .build();
    }

    private ProcessStatus getProcessStatus(){
        return ProcessStatus.builder()
                .idProcessStatus(uuidProcessStatus)
                .name("NEW")
                .description("Новый процесс")
                .build();
    }

    private Process getProcess(UUID uuidProcess){
        return Process.builder()
                .idProcess(uuidProcess)
                .build();
    }

    private ProcessResponse getProcessResponse(UUID uuidProcess){
        return new ProcessResponse()
                .idProcess(uuidProcess.toString());
    }

}