package com.griekat.currencyrate.api.rest.converter;

import com.griekat.currencyrate.api.rest.representation.CurrencyConversionRepresentation;
import com.griekat.currencyrate.model.external.ForeignExchangeRatesResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CurrencyConversionConverter implements Converter<ForeignExchangeRatesResult, CurrencyConversionRepresentation> {
    @Override
    public CurrencyConversionRepresentation convert(ForeignExchangeRatesResult foreignExchangeRatesResult) {
        return CurrencyConversionRepresentation.builder()
                .currencyRate(convertRate(foreignExchangeRatesResult))
                .date(foreignExchangeRatesResult.getDate())
                .build();
    }

    private CurrencyConversionRepresentation.CurrencyRate convertRate(ForeignExchangeRatesResult ratesResult) {
        Map.Entry<String, BigDecimal> rate = ratesResult.getRates().entrySet().iterator().next();
        return CurrencyConversionRepresentation.CurrencyRate.builder()
                .baseCurrency(ratesResult.getBase())
                .baseCurrencyValue(BigDecimal.ONE)
                .targetCurrency(rate.getKey())
                .targetCurrencyValue(rate.getValue())
                .build();
    }
}
