package com.griekat.currencyrate.api.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@ApiModel(value = "CurrencyConversionRequest", description = "Запрос на конвертацию валюты на конкретную дату")
public class CurrencyConversionRequest {

    @ApiModelProperty(value = "Базовая валюта", position = 10)
    private String baseCurrency;

    @NotNull
    @ApiModelProperty(value = "Валюта конвертации", position = 20)
    private String targetCurrency;

    @NotNull
    @ApiModelProperty(value = "Сумма", position = 30)
    private BigDecimal amount;

    @ApiModelProperty(value = "Дата конвертации", position = 40)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

}
