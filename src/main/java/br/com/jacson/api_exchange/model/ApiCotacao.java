package br.com.jacson.api_exchange.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@Data
public class ApiCotacao implements Serializable {

    @ApiModelProperty(hidden = true)
    private String base;
    @ApiModelProperty(hidden = true)
    private Calendar date;
    @ApiModelProperty(hidden = true)
    private HashMap rates;



    @ApiModelProperty(hidden = true)
    public String dataFormatada(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date.getTime());
    }







}
