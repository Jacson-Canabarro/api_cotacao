package br.com.jacson.api_exchange.controller;

import br.com.jacson.api_exchange.model.Cotacao;
import br.com.jacson.api_exchange.model.dto.CotacaoDTO;
import br.com.jacson.api_exchange.service.ExchangeService;
import br.com.jacson.api_exchange.utils.Resultado;
import br.com.jacson.api_exchange.utils.ValidatorUtils;
import br.com.jacson.api_exchange.validator.CotacaoDTOValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Api(value = "Verificação de cotações por moedas", tags = "Cotação pares de moedas")
@RestController
@RequestMapping("/cotacao")
@AllArgsConstructor
public class ExchangeRestController {


    private final ExchangeService exchangeService;
    private final CotacaoDTOValidator cotacaoDTOValidator;


    @InitBinder("cotacaoDTO")
    public void initBinder(WebDataBinder webDataBinder) {
        // Registra validador
        webDataBinder.setValidator(cotacaoDTOValidator);
    }

    @GetMapping("/moedas")
    @ApiOperation(value = "Resulta nas singlas das moedas disponíveis.",
            response = List.class,
            notes = "Endpoint utilizado para consultar as singlas de moedas disponíveis para consultar a cotação.")
    public List<String> getListaMoedas(){
        return CotacaoDTOValidator.list;
    }

    @GetMapping
    @ApiOperation(value = "Resulta em um objeto paginável com todas as consultas realizadas na API.",
            response = List.class,
            notes = "Endpoint utilizado para consultar os registros feitos na API e salvos na base de dados.")
    public Page<Cotacao> getAllCotacoes(){
        return exchangeService.getAllCotacoes();
    }

    @PostMapping
    @ApiOperation(value = "Consulta a cotação de acordo com os dados passados na entidade formato application/json.",
            response = Resultado.class,
            notes = "Endpoint utilizado para consultar a cotação do par de moedas, essa operação geralmente é executada quando todos os campos são " +
                    "enviados de uma só vez. \n\n Essa operação gera uma nova consulta a cada chamada. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cotação consultada com sucesso"),
            @ApiResponse(code = 400, message = "Preenchimento dos campos inválidos")})
    public Resultado<CotacaoDTO> getCotacao(@RequestBody @Valid CotacaoDTO dto, BindingResult result) throws ParseException {
        ValidatorUtils.verifyBindingResult(result);
       return new Resultado<>(exchangeService.getCotacao(dto));
    }
}
