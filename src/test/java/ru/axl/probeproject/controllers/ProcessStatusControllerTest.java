package ru.axl.probeproject.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.axl.probeproject.controllers.base.BaseControllerTest;
import ru.axl.probeproject.exceptions.GlobalExceptionHandler;
import ru.axl.probeproject.model.ProcessStatusResponse;
import ru.axl.probeproject.services.ProcessStatusService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class ProcessStatusControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private ProcessStatusController processStatusController;

    @Mock
    private ProcessStatusService processStatusService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(processStatusController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenGetAllProcessStatusesThen200() throws Exception {
        final List<ProcessStatusResponse> processStatusResp = List.of(
                new ProcessStatusResponse().idProcessStatus(UUID.randomUUID().toString()),
                new ProcessStatusResponse().idProcessStatus(UUID.randomUUID().toString()),
                new ProcessStatusResponse().idProcessStatus(UUID.randomUUID().toString())
        );

        when(processStatusService.getAllProcessStatuses()).thenReturn(processStatusResp);

        final MockHttpServletResponse resp = mvc.perform(get("/process-statuses")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        final List<ProcessStatusResponse> respAct = objectMapper.readValue(resp.getContentAsString(), new TypeReference<>() { });
        assertThat(respAct).hasSize(3);
        assertThat(respAct).containsAll(processStatusResp);
    }

}