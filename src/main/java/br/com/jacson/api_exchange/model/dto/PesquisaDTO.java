package br.com.jacson.api_exchange.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PesquisaDTO implements Serializable {

    private Integer pag;
    private String sort;
    private Boolean asc;
}
