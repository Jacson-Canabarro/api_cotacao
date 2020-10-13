package br.com.jacson.api_exchange.utils;


import br.com.jacson.api_exchange.exception.ValidacaoException;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidatorUtils {


    /**
     * Verifica o BindingResult enviado como parâmetro. Caso haja um erro de validação
     * uma ValidacaoException é lançada.
     *
     * @param result
     * @throws ValidacaoException
     */

    public static void verifyBindingResult(BindingResult result) throws ValidacaoException {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            result.getFieldErrors().forEach(f -> erros.put(f.getField(), f.getDefaultMessage()));
            result.getGlobalErrors().forEach(f -> erros.put(f.getObjectName(), f.getDefaultMessage()));
            throw new ValidacaoException(erros);
        }
    }

}
