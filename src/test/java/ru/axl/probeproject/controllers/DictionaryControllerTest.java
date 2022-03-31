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
import ru.axl.probeproject.model.CurrencyResponse;
import ru.axl.probeproject.services.CurrencyService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DictionaryControllerTest extends BaseControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private DictionaryController dictionaryController;

    @Mock
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(dictionaryController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void whenGetCurrencies_thenStatus200() throws Exception {
        List<CurrencyResponse> currencyResponses = List.of(
                new CurrencyResponse(),
                new CurrencyResponse()
        );

        when(currencyService.getCurrencies()).thenReturn(currencyResponses);

        MockHttpServletResponse resp = mvc.perform(get("/dictionaries/currencies")
                        .contentType(APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(resp.getStatus()).isEqualTo(OK.value());

        List<CurrencyResponse> respList = objectMapper.readValue(resp.getContentAsString(), new TypeReference<>() {});
        assertThat(respList).hasSize(2);
        assertThat(respList).isEqualTo(currencyResponses);
    }

    @Test
    void whenGetCurrenciesEmptyList_thenStatus200() throws Exception {
        when(currencyService.getCurrencies()).thenReturn(List.of());

        mvc.perform(get("/dictionaries/currencies")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetCurrencies_thenStatus500() throws Exception {
        when(currencyService.getCurrencies()).thenThrow(new RuntimeException());

        mvc.perform(get("/dictionaries/currencies")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

}