package br.com.jacson.api_exchange.validator;

import br.com.jacson.api_exchange.model.dto.CotacaoDTO;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;


@Component
public class CotacaoDTOValidator implements Validator {

    public static final List<String> list = Arrays.asList("EUR", "CAD", "HKD", "ISK", "PHP", "DKK", "HUF", "CZK", "AUD", "RON", "SEK", "IDR", "INR", "BRL", "RUB", "HRK", "JPY", "THB", "CHF", "SGD", "PLN", "BGN", "TRY", "CNY", "NOK", "NZD", "ZAR", "USD", "MXN", "ILS", "GBP", "KRW", "MYR");

    @Override
    public boolean supports(Class<?> aClass) {
        return CotacaoDTO.class.equals(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {

        CotacaoDTO cotacaoDTO = (CotacaoDTO) target;


        if (ObjectUtils.isEmpty(cotacaoDTO.getDataCotacao())) {
            errors.rejectValue("dataCotacao", "dataCotacao", "Preencha a data para cotação.");
        } else if (!GenericValidator.isDate(cotacaoDTO.getDataCotacao(), "dd/MM/yyyy", true)) {
            errors.rejectValue("dataCotacao", "dataCotacao", "Informa a data no formato válido (DIA/MÊS/ANO Ex: 25/09/2020).");
        }
        if (ObjectUtils.isEmpty(cotacaoDTO.getMoedaBase())) {
            errors.rejectValue("moedaBase", "moedaBase", "Preencha a moeda base para cotação.");
        } else if (!list.contains(cotacaoDTO.getMoedaBase())) {
            errors.rejectValue("moedaBase", "moedaBase", "A singla da moeda informada é inválida.");
        }
        if (ObjectUtils.isEmpty(cotacaoDTO.getMoedaFinal())) {
            errors.rejectValue("moedaFinal", "moedaFinal", "Preencha a moeda.");
        } else if (!list.contains(cotacaoDTO.getMoedaFinal())) {
            errors.rejectValue("moedaFinal", "moedaFinal", "A singla da moeda informada é inválida.");
        }

        if (ObjectUtils.isEmpty(cotacaoDTO.getQuantidade()) || cotacaoDTO.getQuantidade() <= 0) {
            errors.rejectValue("quantidade", "quantidade", "Preencha a quantidade de moedas.");
        }
    }
}
