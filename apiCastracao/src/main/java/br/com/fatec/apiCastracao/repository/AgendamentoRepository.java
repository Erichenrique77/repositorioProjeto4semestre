package br.com.fatec.apiCastracao.repository;

import br.com.fatec.apiCastracao.modelo.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
