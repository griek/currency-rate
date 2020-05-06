package com.griekat.currencyrate.service;

import com.griekat.currencyrate.api.rest.representation.CurrencyConversionRepresentation;
import com.griekat.currencyrate.api.rest.request.CurrencyConversionRequest;
import com.griekat.currencyrate.model.external.ForeignExchangeRatesResult;
import com.griekat.currencyrate.service.external.ForeignExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Service
@Validated
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final ForeignExchangeRatesService foreignExchangeRatesService;

    /**
     * Возвращает курс валют для заданной даты и валюты с внешнего сервиса.
     */
    public CurrencyConversionRepresentation convertCurrency(@NotNull @Valid CurrencyConversionRequest request,
                                                            @NotNull Converter<ForeignExchangeRatesResult, CurrencyConversionRepresentation> converter) {
        CurrencyConversionRepresentation representation = converter.convert(foreignExchangeRatesService.converting(request));
        calculateNewAmountForRate(Objects.requireNonNull(representation), request.getAmount());
        return representation;

    }

    /* Вычисляем новое значение суммы на основе пришедшего курса валюты */
    private void calculateNewAmountForRate(CurrencyConversionRepresentation representation, BigDecimal amount) {
        BigDecimal newAmount = amount.multiply(representation.getCurrencyRate().getTargetCurrencyValue());
        representation.setAmount(newAmount);
    }
}
