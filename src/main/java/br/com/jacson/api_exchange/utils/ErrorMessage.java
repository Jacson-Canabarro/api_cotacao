package br.com.jacson.api_exchange.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>Clase utilizada para guardar erros de validação ou de execução.</p>
 */
@Getter
@Setter
public class ErrorMessage implements Serializable {

    @ApiModelProperty(hidden = true)
    private String message;
    @ApiModelProperty(hidden = true)
    private Map<String, String> erros;

    public ErrorMessage() {

    }

    public ErrorMessage(String message, Map<String, String> erros) {
        this.message = message;
        this.erros = erros;
    }
}
