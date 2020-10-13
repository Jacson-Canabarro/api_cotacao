package br.com.jacson.api_exchange.service;

import br.com.jacson.api_exchange.model.ApiCotacao;
import br.com.jacson.api_exchange.model.Cotacao;
import br.com.jacson.api_exchange.model.dto.CotacaoDTO;
import br.com.jacson.api_exchange.repository.CotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;

@Service
@AllArgsConstructor
public class ExchangeService {

    private final String MINHA_CONSULTA = "https://api.exchangeratesapi.io/2020-04-04?base=USD&symbols=BRL";

    private final UriComponentsBuilder uriComponentsBuilder;
    private final CotacaoRepository cotacaoRepository;


    public CotacaoDTO getCotacao(CotacaoDTO cotacaoDTO) throws ParseException {
        String url = uriComponentsBuilder.path("/"+cotacaoDTO.converterData())
                .queryParam("base", cotacaoDTO.getMoedaBase())
                .queryParam("symbols", cotacaoDTO.getMoedaFinal()).build().toString();
        RestTemplate restTemplate = new RestTemplate();
        ApiCotacao apiCotacao = restTemplate.getForObject(url, ApiCotacao.class);
        if (!ObjectUtils.isEmpty(apiCotacao)) {
            cotacaoDTO.setDataCotacao(apiCotacao.dataFormatada());
            cotacaoDTO.calcularConversao(apiCotacao);
            Cotacao cotacao = new Cotacao(cotacaoDTO);
            cotacaoRepository.save(cotacao);
        }
        return cotacaoDTO;
    }

    public Page<Cotacao> getAllCotacoes(){
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,"dataConsulta");
         return cotacaoRepository.findAll(pageRequest);
    }
}
