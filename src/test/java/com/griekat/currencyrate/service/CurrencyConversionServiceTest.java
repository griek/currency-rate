package com.griekat.currencyrate.service;

import com.griekat.currencyrate.api.rest.converter.CurrencyConversionConverter;
import com.griekat.currencyrate.api.rest.representation.CurrencyConversionRepresentation;
import com.griekat.currencyrate.api.rest.request.CurrencyConversionRequest;
import com.griekat.currencyrate.model.external.ForeignExchangeRatesResult;
import com.griekat.currencyrate.service.external.ForeignExchangeRatesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CurrencyConversionServiceTest {

    private CurrencyConversionService currencyConversionService;
    private ForeignExchangeRatesService foreignExchangeRatesService = Mockito.mock(ForeignExchangeRatesService.class);
    private CurrencyConversionConverter converter = new CurrencyConversionConverter();

    @Before
    public void init() {
        currencyConversionService = new CurrencyConversionService(foreignExchangeRatesService);
    }

    @Test
    public void getConversionForRate_thenOk() {
        LocalDate now = LocalDate.now();
        ForeignExchangeRatesResult expected = new ForeignExchangeRatesResult();
        expected.setBase("USD");
        expected.setDate(now);
        expected.setRates(Map.of("RUB", BigDecimal.valueOf(72.8765)));

        when(foreignExchangeRatesService.converting(any(CurrencyConversionRequest.class))).thenReturn(expected);

        CurrencyConversionRequest request = new CurrencyConversionRequest();
        request.setBaseCurrency("USD");
        request.setTargetCurrency("RUB");
        request.setAmount(BigDecimal.valueOf(100));

        CurrencyConversionRepresentation result = currencyConversionService.convertCurrency(request, converter);

        assertThat(result).isNotNull();
        assertThat(result.getDate()).isEqualTo(now);
        assertThat(result.getCurrencyRate().getTargetCurrencyValue()).isEqualTo(BigDecimal.valueOf(72.8765));
    }
}
