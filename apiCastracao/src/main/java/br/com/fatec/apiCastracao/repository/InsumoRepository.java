package br.com.fatec.apiCastracao.repository;

import br.com.fatec.apiCastracao.modelo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
}
