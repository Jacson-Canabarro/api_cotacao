package br.com.jacson.api_exchange.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Classe que representa o resultado de uma operação, principalmente de salvar ou editar entidades.</p>
 *
 * @param <T> Tipo do objeto resultado.
 */
@Getter
@Setter
public class Resultado<T> {

    @ApiModelProperty(hidden = true)
    private T objetoResultado;
    @ApiModelProperty(hidden = true)
    private ErrorMessage errorMessage;

    public Resultado() {
    }

    public Resultado(T objetoResultado) {
        this.objetoResultado = objetoResultado;
    }

    public Resultado(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

}
