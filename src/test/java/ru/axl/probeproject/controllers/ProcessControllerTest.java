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
import ru.axl.probeproject.model.ProcessRequest;
import ru.axl.probeproject.model.ProcessResponse;
import ru.axl.probeproject.services.ProcessService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class ProcessControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private ProcessController processController;

    @Mock
    private ProcessService processService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(processController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAllClientProcesses() throws Exception {
        UUID uuidClient = UUID.randomUUID();

        List<ProcessResponse> processResponses = List.of(
                new ProcessResponse().idProcess(UUID.randomUUID().toString()),
                new ProcessResponse().idProcess(UUID.randomUUID().toString()),
                new ProcessResponse().idProcess(UUID.randomUUID().toString())
        );

        when(processService.findAllClientProcesses(uuidClient)).thenReturn(processResponses);

        MockHttpServletResponse resp = mvc.perform(get("/processes/clients/" + uuidClient)
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        List<ProcessResponse> processRespAct = objectMapper.readValue(resp.getContentAsString(), new TypeReference<>() {});

        assertThat(processRespAct).hasSize(3);
        assertThat(processRespAct).containsAll(processResponses);
    }

    @Test
    void postProcess() throws Exception {
        final String idClient = UUID.randomUUID().toString();
        ProcessResponse processResponse = new ProcessResponse().idProcess(UUID.randomUUID().toString());
        ProcessRequest processRequest = new ProcessRequest().idClient(idClient);

        when(processService.createProcess(processRequest)).thenReturn(processResponse);

        MockHttpServletResponse resp = mvc.perform(post("/processes")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(processRequest)))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(CREATED.value());

        ProcessResponse processRespAct = objectMapper.readValue(resp.getContentAsString(), ProcessResponse.class);
        assertThat(processRespAct).isEqualTo(processResponse);
    }

}