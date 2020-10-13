package br.com.jacson.api_exchange.controller;

import br.com.jacson.api_exchange.exception.NotFoundException;
import br.com.jacson.api_exchange.exception.ValidacaoException;
import br.com.jacson.api_exchange.utils.ErrorMessage;
import br.com.jacson.api_exchange.utils.Resultado;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * <p>Advice que trata globalmente os erros esperados da API.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    /**
     * <p>Método que trata os erros de validação, construindo a resposta apropriada.</p>
     *
     * @param ex Exceção de validação
     * @return Resposta com os erros de validação
     */
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Resultado> handleValidacaoException(ValidacaoException ex){
        return ResponseEntity
                .status(400)
                .body(new Resultado(
                        new ErrorMessage("Erros de validação.",
                                ex.getErros())));
    }


    /**
     * <p>Método que trata erro de objeto não encontrado.</p>
     *
     * @param ex
     * @return Resposta com o ID da entidade não encontrada.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Resultado> handleNotFoundException(NotFoundException ex){
        return ResponseEntity
                .status(404)
                .body(new Resultado(
                        new ErrorMessage("Objeto não encontrado",
                                Collections.singletonMap("404", ex.getMessage()))));
    }


    /**
     * <p>Método que trata erros de leitura no corpo da requisição.
     * Geralmente ocorre se um enum ser enviado vazio no JSON.</p>
     *
     * @return Resultado com mensagem genérica de erro.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Resultado> handleMessageNotReadableException(){
        return ResponseEntity
                .status(400)
                .body(new Resultado(
                        new ErrorMessage("Erro inesperado.",
                                Collections.singletonMap("500", "Um erro inesperado ocorreu, tente novamente."))));
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Resultado> handleUnsupporterOperationException(){
        return ResponseEntity
                .status(400)
                .body(new Resultado(
                                new ErrorMessage("Error de formatação",
                                        Collections.singletonMap("400", "Um erro de formatação da requisição ocorreu."))
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Resultado> handleOtherException() {
        return ResponseEntity
                .status(500)
                .body(new Resultado(
                        new ErrorMessage("Erro inesperado",
                                Collections.singletonMap("500", "Ocorreu um erro inesperado. Por favor, tente novamente."))
                ));
    }
}
