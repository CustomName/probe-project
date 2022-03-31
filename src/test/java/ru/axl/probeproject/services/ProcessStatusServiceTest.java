package ru.axl.probeproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.axl.probeproject.mapper.ProcessStatusMapper;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.model.entities.ProcessStatus;
import ru.axl.probeproject.repositories.ProcessStatusRepository;
import ru.axl.probeproject.services.base.BaseServiceTest;
import ru.axl.probeproject.services.impl.ProcessStatusServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ProcessStatusServiceTest extends BaseServiceTest {

    @InjectMocks
    private ProcessStatusServiceImpl processStatusService;

    @Mock
    private ProcessStatusRepository processStatusRepo;

    @Mock
    private ProcessStatusMapper processStatusMapper;

    @Test
    void getAllProcessStatuses() {
        ProcessStatus processStatus1 = ProcessStatus.builder()
                .idProcessStatus(UUID.randomUUID())
                .name("NEW")
                .description("Новый процесс")
                .build();

        ProcessStatus processStatus2 = ProcessStatus.builder()
                .idProcessStatus(UUID.randomUUID())
                .name("COMPLIANCE_SUCCESS")
                .description("Комплаенс проверка успешно выполнена")
                .build();

        ProcessStatusResponse processStatusResp1 = new ProcessStatusResponse()
                .idProcessStatus(processStatus1.getIdProcessStatus().toString())
                .name(processStatus1.getName())
                .description(processStatus1.getDescription());

        ProcessStatusResponse processStatusResp2 = new ProcessStatusResponse()
                .idProcessStatus(processStatus2.getIdProcessStatus().toString())
                .name(processStatus2.getName())
                .description(processStatus2.getDescription());

        List<ProcessStatus> processStatuses = List.of(processStatus1, processStatus2);
        List<ProcessStatusResponse> processStatusResponses = List.of(processStatusResp1, processStatusResp2);

        when(processStatusMapper.toProcessStatusResponseList(processStatuses)).thenReturn(processStatusResponses);
        when(processStatusRepo.findAll()).thenReturn(processStatuses);

        List<ProcessStatusResponse> allProcessStatuses = processStatusService.getAllProcessStatuses();
        assertThat(allProcessStatuses).hasSize(2);

        assertThat(allProcessStatuses).containsAll(processStatusResponses);
    }

}