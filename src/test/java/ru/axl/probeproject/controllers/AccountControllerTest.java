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
import ru.axl.probeproject.model.AccountOpenRequest;
import ru.axl.probeproject.model.AccountReserveRequest;
import ru.axl.probeproject.model.AccountResponse;
import ru.axl.probeproject.services.AccountService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class AccountControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenGetAllClientAccountsThenStatus200() throws Exception {
        final List<AccountResponse> accountResponses = List.of(
                new AccountResponse().idAccount(UUID.randomUUID().toString()),
                new AccountResponse().idAccount(UUID.randomUUID().toString()),
                new AccountResponse().idAccount(UUID.randomUUID().toString())
        );
        when(accountService.findAllClientAccounts(any())).thenReturn(accountResponses);

        final MockHttpServletResponse response = mvc.perform(get("/accounts/clients/7d56e2ba-855e-42a7-bb71-0540ddac38ef")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

        final List<AccountResponse> respList = objectMapper.readValue(response.getContentAsString(), new TypeReference<>() { });
        assertThat(respList).hasSize(3);
        assertThat(respList).isEqualTo(accountResponses);
    }

    @Test
    void whenPostAccountReserveThenStatus200() throws Exception {
        final AccountResponse accountResponse = new AccountResponse()
                .idAccount(UUID.randomUUID().toString());
        final AccountReserveRequest accountRequest = new AccountReserveRequest()
                .idClient(UUID.randomUUID().toString());

        when(accountService.reserveAccount(accountRequest)).thenReturn(accountResponse);

        final MockHttpServletResponse response = mvc.perform(post("/accounts/reserve")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(CREATED.value());

        final AccountResponse accountRespAct = objectMapper.readValue(response.getContentAsString(), AccountResponse.class);
        assertThat(accountRespAct).isEqualTo(accountResponse);
    }

    @Test
    void whenPatchAccountOpenThenStatus200() throws Exception {
        final AccountOpenRequest req = new AccountOpenRequest()
                .idClient(UUID.randomUUID().toString());
        final List<AccountResponse> accRespExp = List.of(
                new AccountResponse()
                        .idAccount(UUID.randomUUID().toString()),
                new AccountResponse()
                        .idAccount(UUID.randomUUID().toString())
        );

        when(accountService.openAccounts(req)).thenReturn(accRespExp);

        final MockHttpServletResponse resp = mvc.perform(patch("/accounts/open")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        final List<AccountResponse> accRespAct = objectMapper.readValue(resp.getContentAsString(),
                new TypeReference<>() { });
        assertThat(accRespAct).isEqualTo(accRespExp);
    }

}