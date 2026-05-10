package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.AgendamentoDTO;
import br.com.fatec.apiCastracao.modelo.Agendamento;
import br.com.fatec.apiCastracao.modelo.Animal;
import br.com.fatec.apiCastracao.modelo.Responsavel;
import br.com.fatec.apiCastracao.repository.AgendamentoRepository;
import br.com.fatec.apiCastracao.repository.AnimalRepository;
import br.com.fatec.apiCastracao.repository.ResponsavelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AgendamentoController {
    @Autowired
    AgendamentoRepository repositorioAgendamento;

    @Autowired
    ResponsavelRepository repositorioResponsavel;

    @Autowired
    AnimalRepository repositorioAnimal;

    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody AgendamentoDTO dto) {

        var agendamento = new Agendamento();

        // Copia apenas campos simples
        BeanUtils.copyProperties(dto, agendamento, "responsavelId", "animalId");

        // Relacionamento: Responsável
        if (dto.responsavelId() != null) {
            Responsavel responsavel = repositorioResponsavel.findById(dto.responsavelId())
                    .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
            agendamento.setResponsavel(responsavel);
        }

        // Relacionamento: Animal
        if (dto.animalId() != null) {
            Animal animal = repositorioAnimal.findById(dto.animalId())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            agendamento.setAnimal(animal);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repositorioAgendamento.save(agendamento));
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<Agendamento>> getAllAgendamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAgendamento.findAll());
    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<Object> getAgendamentoPorId(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }

    @PutMapping("/agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(
            @PathVariable Integer id,
            @RequestBody AgendamentoDTO dto) {

        var agendamento = repositorioAgendamento.findById(id).orElse(null);

        if (agendamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agendamento de id " + id + " não existe.");
        }

        BeanUtils.copyProperties(dto, agendamento, "id", "responsavelId", "animalId");

        // Atualiza responsável
        if (dto.responsavelId() != null) {
            Responsavel responsavel = repositorioResponsavel.findById(dto.responsavelId())
                    .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
            agendamento.setResponsavel(responsavel);
        }

        // Atualiza animal
        if (dto.animalId() != null) {
            Animal animal = repositorioAnimal.findById(dto.animalId())
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
            agendamento.setAnimal(animal);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repositorioAgendamento.save(agendamento));
    }

    @DeleteMapping ("/agendamento/{id}")
    public ResponseEntity<Object> excluirAgendamento(@PathVariable(value="id") Integer id){
        Optional<Agendamento> agendamento = repositorioAgendamento.findById(id);
        if (agendamento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento de id "+id+" não existe.");
        }
        repositorioAgendamento.delete(agendamento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento id "+id+" removido com sucesso!");
    }
}
