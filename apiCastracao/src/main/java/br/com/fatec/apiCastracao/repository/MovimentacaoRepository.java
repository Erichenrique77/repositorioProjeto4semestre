package br.com.fatec.apiCastracao.repository;

import br.com.fatec.apiCastracao.modelo.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
}
