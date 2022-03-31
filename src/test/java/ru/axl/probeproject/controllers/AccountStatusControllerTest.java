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
import ru.axl.probeproject.model.AccountStatusResponse;
import ru.axl.probeproject.services.AccountStatusService;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class AccountStatusControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private AccountStatusController accountStatusController;

    @Mock
    private AccountStatusService accountStatusService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(accountStatusController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenGetAllAccountStatuses_thenStatus200() throws Exception {
        List<AccountStatusResponse> accountStatusResp = List.of(
                new AccountStatusResponse().idAccountStatus(UUID.randomUUID().toString()),
                new AccountStatusResponse().idAccountStatus(UUID.randomUUID().toString()),
                new AccountStatusResponse().idAccountStatus(UUID.randomUUID().toString())
        );

        when(accountStatusService.getAllAccountStatuses()).thenReturn(accountStatusResp);

        MockHttpServletResponse resp = mvc.perform(get("/account-statuses")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        List<AccountStatusResponse> respList = objectMapper.readValue(resp.getContentAsString(), new TypeReference<>() {});
        assertThat(respList).hasSize(3);
        assertThat(respList).isEqualTo(accountStatusResp);
    }

}