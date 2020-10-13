package br.com.jacson.api_exchange.repository;

import br.com.jacson.api_exchange.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Integer> {
}
