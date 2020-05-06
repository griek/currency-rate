package com.griekat.currencyrate.api.rest;


import com.griekat.currencyrate.api.rest.converter.CurrencyConversionConverter;
import com.griekat.currencyrate.api.rest.representation.CurrencyConversionRepresentation;
import com.griekat.currencyrate.api.rest.request.CurrencyConversionRequest;
import com.griekat.currencyrate.service.CurrencyConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency-conversion")
@Api(tags = "currency-conversion")
@SwaggerDefinition(tags = {@Tag(name = "currency-conversion", description = "Конвертация валюты")})
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyConversionConverter converter;
    private final CurrencyConversionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запросить конвертацию валюты на конкретную дату")
    public CurrencyConversionRepresentation getCurrencyRateForDate(@RequestBody CurrencyConversionRequest request) {
        return service.convertCurrency(request, converter);
    }
}
