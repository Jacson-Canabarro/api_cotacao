package br.com.jacson.api_exchange.model;

import br.com.jacson.api_exchange.model.dto.CotacaoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
@Table
@Entity
public class Cotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(hidden = true)
    private String dataCotacao;
    @ApiModelProperty(hidden = true)
    private String moedaBase;
    @ApiModelProperty(hidden = true)
    private String moedaFinal;
    @ApiModelProperty(hidden = true)
    private Integer quantidade;
    @ApiModelProperty(hidden = true)
    private String total_convertido;
    @ApiModelProperty(hidden = true)
    private String cotacao_dia;

    @ApiModelProperty(hidden = true)
    private Calendar dataConsulta;

    public Cotacao() {
    }

    public Cotacao(CotacaoDTO dto) {
        this.dataCotacao = dto.getDataCotacao();
        this.moedaBase = dto.getMoedaBase();
        this.moedaFinal = dto.getMoedaFinal();
        this.quantidade = dto.getQuantidade();
        this.total_convertido = dto.getTotal_convertido();
        this.cotacao_dia = dto.getCotacao_dia();
        this.dataConsulta = Calendar.getInstance();
    }
}
