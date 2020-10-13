package br.com.jacson.api_exchange.model.dto;

import br.com.jacson.api_exchange.model.ApiCotacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel(description = "Modelo que representa os campos do formulário, as informações necessárias para consultar a cotação.")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CotacaoDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "A data deve ser informada no formato (DIA/MÊS/ANO) para consultar uma determinada cotação.", required = true, example = "28/11/2018")
    private String dataCotacao;
    @ApiModelProperty(position = 2, notes = "Trata-se da moeda que será convertida na consulta.", required = true, example = "USD")
    private String moedaBase;
    @ApiModelProperty(position = 3, notes = "Moeda resultante para cotação.", required = true, example = "BRL")
    private String moedaFinal;
    @ApiModelProperty(position = 4, notes = "Quantidade de moedas para ser calculada.",required = true,example = "25")
    private Integer quantidade;
    @ApiModelProperty(hidden = true)
    private String total_convertido;
    @ApiModelProperty(hidden = true)
    private String cotacao_dia;


    @ApiModelProperty(hidden = true)
    public void calcularConversao(ApiCotacao apiCotacao){
        if(!ObjectUtils.isEmpty(apiCotacao.getRates())){
            Double valor = (Double) apiCotacao.getRates().get(moedaFinal);
            DecimalFormat df = new DecimalFormat("#0.00");
            cotacao_dia = moedaFinal +": " + df.format(valor);
            double resultado = valor * quantidade;
            total_convertido = moedaFinal +": " +df.format(resultado) ;
        }
    }

    public String converterData() throws ParseException {
        DateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse(dataCotacao);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }



}
