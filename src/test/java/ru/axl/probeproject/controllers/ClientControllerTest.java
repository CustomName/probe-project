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
import ru.axl.probeproject.model.ClientResponse;
import ru.axl.probeproject.services.ClientService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class ClientControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenGetClientsThen200() throws Exception {
        final List<ClientResponse> clientResponses = List.of(
                new ClientResponse().idClient(UUID.randomUUID().toString()),
                new ClientResponse().idClient(UUID.randomUUID().toString()),
                new ClientResponse().idClient(UUID.randomUUID().toString())
        );

        when(clientService.findAll()).thenReturn(clientResponses);

        final MockHttpServletResponse resp = mvc.perform(get("/clients")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        final List<ClientResponse> clientResponsesAct = objectMapper.readValue(resp.getContentAsString(), new TypeReference<>() { });

        assertThat(clientResponsesAct).hasSize(3);
        assertThat(clientResponsesAct).containsAll(clientResponses);
    }

    @Test
    void whenFindClientByInnThen200() throws Exception {
        final ClientResponse clientResponse = new ClientResponse()
                .idClient(UUID.randomUUID().toString())
                .inn("111111111111")
                .fio("Smith Jonson Jonsonovich");

        when(clientService.findByInn(clientResponse.getInn())).thenReturn(clientResponse);

        final MockHttpServletResponse resp = mvc.perform(get("/clients/inn/111111111111")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        final ClientResponse clientRespAct = objectMapper.readValue(resp.getContentAsString(), ClientResponse.class);

        assertThat(resp.getStatus()).isEqualTo(OK.value());
        assertThat(clientRespAct).isEqualTo(clientResponse);
    }

}