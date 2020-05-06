package com.griekat.currencyrate.exception;

public class ForeignExchangeRatesServiceException extends RuntimeException {
    private static final long serialVersionUID = 1906876698371314678L;

    public ForeignExchangeRatesServiceException(String message) {
        super(message);
    }

    public ForeignExchangeRatesServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
