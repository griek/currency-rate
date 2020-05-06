package com.griekat.currencyrate.api.rest.representation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ApiModel(value = "CurrencyConversionRepresentation", description = "Результат конвертации валюты")
public class CurrencyConversionRepresentation {

    @ApiModelProperty(value = "Курс", position = 10)
    private CurrencyRate currencyRate;

    @ApiModelProperty(value = "Дата", position = 20)
    private LocalDate date;

    @Setter
    @ApiModelProperty(value = "Сумма после конвертации", position = 30)
    private BigDecimal amount;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Getter
    @ApiModel(value = "CurrencyConversionRepresentation.CurrencyRate", description = "Курс")
    public static class CurrencyRate {
        @ApiModelProperty(value = "Базовая валюта", position = 10)
        private String baseCurrency;
        @ApiModelProperty(value = "Значение базовой валюты", position = 20)
        private BigDecimal baseCurrencyValue;
        @ApiModelProperty(value = "Валюта конвертации", position = 30)
        private String targetCurrency;
        @ApiModelProperty(value = "Значение валюты конвертации", position = 40)
        private BigDecimal targetCurrencyValue;
    }
}
