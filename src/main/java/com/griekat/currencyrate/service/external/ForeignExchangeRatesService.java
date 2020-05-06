package com.griekat.currencyrate.service.external;

import com.griekat.currencyrate.api.rest.request.CurrencyConversionRequest;
import com.griekat.currencyrate.exception.ForeignExchangeRatesServiceException;
import com.griekat.currencyrate.model.external.ForeignExchangeRatesResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ForeignExchangeRatesService {
    private static final Logger LOGGER = Logger.getLogger(ForeignExchangeRatesService.class.getName());

    private static final String SLASH = "/";
    private static final String QUESTION_MARK = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final RestTemplate restTemplate;

    @Value("${external.foreign.currency.rates.service.url}")
    private String serviceUrl;
    @Value("${service.current.date.url}")
    private String currentDateUrl;
    @Value("${service.base.currency.url}")
    private String baseCurrencyUrl;
    @Value("${service.target.currency.url}")
    private String targetCurrencyUrl;
    @Value("${default.currency.code}")
    private String defaultCurrency;

    @Cacheable("currency-rates")
    public ForeignExchangeRatesResult converting(CurrencyConversionRequest request) {

        ResponseEntity<ForeignExchangeRatesResult> response;
        try {
            response = restTemplate.getForEntity(prepareRequestUrl(request), ForeignExchangeRatesResult.class);
        } catch (RestClientException ex) {
            LOGGER.log(Level.SEVERE, "Foreign Exchange Rates Service ERROR = {0}", ex.getMessage());
            throw new ForeignExchangeRatesServiceException("Ошибка выполнения запроса на внешнем сервисе Foreign Exchange Rates: " + ex.getMessage());
        }
        return response.getBody();
    }

    /* Подготавливаем строку запроса на основе пришедшего запроса CurrencyConversionRequest */
    private String prepareRequestUrl(CurrencyConversionRequest request) {
        assert (Objects.isNull(request.getTargetCurrency()));

        StringBuilder sb = new StringBuilder(serviceUrl).append(SLASH);

        if (Objects.nonNull(request.getDate()) && request.getDate().isBefore(LocalDate.now())) {
            sb.append(request.getDate().format(pattern));
        } else {
            sb.append(currentDateUrl);
        }
        sb.append(QUESTION_MARK);
        if (StringUtils.isEmpty(request.getBaseCurrency())) {
            sb.append(baseCurrencyUrl).append(EQUAL).append(defaultCurrency);
        } else {
            sb.append(baseCurrencyUrl).append(EQUAL).append(request.getBaseCurrency());
        }

        sb.append(AMPERSAND).append(targetCurrencyUrl).append(EQUAL).append(request.getTargetCurrency());

        return sb.toString();
    }
}
