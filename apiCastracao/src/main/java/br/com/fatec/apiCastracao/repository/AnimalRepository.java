package br.com.fatec.apiCastracao.repository;

import br.com.fatec.apiCastracao.modelo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}


