package br.com.jacson.api_exchange.exception;



public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
