package br.com.jacson.api_exchange.exception;


import javax.validation.ValidationException;
import java.util.Map;

public class ValidacaoException extends ValidationException {

    private final Map<String, String> erros;

    public ValidacaoException(Map<String, String> erros) {
        super("Erros de validação encontrados");
        this.erros = erros;
    }

    public ValidacaoException(String message, Map<String, String> erros) {
        super(message);
        this.erros = erros;
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
