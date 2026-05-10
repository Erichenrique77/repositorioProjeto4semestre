package br.com.fatec.apiCastracao.repository;


import br.com.fatec.apiCastracao.modelo.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
}
