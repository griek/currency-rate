package com.griekat.currencyrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyrateApplication.class, args);
    }

}
