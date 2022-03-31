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
import ru.axl.probeproject.model.AccountRequest;
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
    void whenGetAllClientAccounts_thenStatus200() throws Exception {
        List<AccountResponse> accountResponses = List.of(
                new AccountResponse().idAccount(UUID.randomUUID().toString()),
                new AccountResponse().idAccount(UUID.randomUUID().toString()),
                new AccountResponse().idAccount(UUID.randomUUID().toString())
        );
        when(accountService.findAllClientAccounts(any())).thenReturn(accountResponses);

        MockHttpServletResponse response = mvc.perform(get("/accounts/clients/7d56e2ba-855e-42a7-bb71-0540ddac38ef")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

        List<AccountResponse> respList = objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {});
        assertThat(respList).hasSize(3);
        assertThat(respList).isEqualTo(accountResponses);
    }

    @Test
    void whenPostAccountReserve_thenStatus200() throws Exception {
        AccountResponse accountResponse = new AccountResponse()
                .idAccount(UUID.randomUUID().toString());
        AccountRequest accountRequest = new AccountRequest()
                .idClient(UUID.randomUUID().toString());

        when(accountService.createAccount(accountRequest)).thenReturn(accountResponse);

        MockHttpServletResponse response = mvc.perform(post("/accounts/reserve")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(CREATED.value());

        AccountResponse accountRespAct = objectMapper.readValue(response.getContentAsString(), AccountResponse.class);
        assertThat(accountRespAct).isEqualTo(accountResponse);
    }

}