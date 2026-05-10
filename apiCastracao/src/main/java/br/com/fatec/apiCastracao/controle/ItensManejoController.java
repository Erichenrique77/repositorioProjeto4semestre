package br.com.fatec.apiCastracao.controle;


import br.com.fatec.apiCastracao.dto.ItensManejoDTO;
import br.com.fatec.apiCastracao.modelo.*;
import br.com.fatec.apiCastracao.repository.AgendamentoRepository;
import br.com.fatec.apiCastracao.repository.ItensManejoRepository;
import br.com.fatec.apiCastracao.repository.ManejoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItensManejoController {
    @Autowired
    ItensManejoRepository repositorioItensManejo;

    @Autowired
    AgendamentoRepository repositorioAgendamento;

    @Autowired
    ManejoRepository repositorioManejo;

    @PostMapping("/itensmanejo")
    public ResponseEntity<ItensManejo> salvarItensManejo(@RequestBody ItensManejoDTO itensManejoDTO){
        var itensManejoModelo = new ItensManejo();

        BeanUtils.copyProperties(itensManejoDTO, itensManejoModelo, "agendamentoId", "manejoId");

        // Relacionamento: Manejo
        if (itensManejoDTO.manejoId() != null) {
            Manejo manejo = repositorioManejo.findById(itensManejoDTO.manejoId())
                    .orElseThrow(() -> new RuntimeException("Manejo não encontrado"));
            itensManejoModelo.setManejo(manejo);
        }

        // Relacionamento: Agendamento
        if (itensManejoDTO.agendamentoId() != null) {
            Agendamento agendamento = repositorioAgendamento.findById(itensManejoDTO.agendamentoId())
                    .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
            itensManejoModelo.setAgendamento(agendamento);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repositorioItensManejo.save(itensManejoModelo));
    }

    @GetMapping("/itensmanejo")
    public ResponseEntity<List<ItensManejo>> getAllItensManejos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioItensManejo.findAll());
    }

    @GetMapping("/itensmanejo/{id}")
    public ResponseEntity<Object> getItensManejoPorId(@PathVariable(value="id") Integer id){
        Optional<ItensManejo> itensManejo = repositorioItensManejo.findById(id);
        if (itensManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ItensManejo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itensManejo.get());
    }

    @PutMapping("/itensmanejo/{id}")
    public ResponseEntity<Object> atualizarItensManejo(
            @PathVariable Integer id,
            @RequestBody ItensManejoDTO itensManejoDTO) {

        var itensManejo = repositorioItensManejo.findById(id).orElse(null);

        if (itensManejo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ItensManejo de id " + id + " não existe.");
        }

        BeanUtils.copyProperties(itensManejoDTO, itensManejo, "id", "manejoId", "agendamentoId");

        // Atualiza Agendamento
        if (itensManejoDTO.agendamentoId() != null) {
            Agendamento agendamento = repositorioAgendamento.findById(itensManejoDTO.agendamentoId())
                    .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
            itensManejo.setAgendamento(agendamento);
        }

        // Atualiza manejo
        if (itensManejoDTO.manejoId() != null) {
            Manejo manejo = repositorioManejo.findById(itensManejoDTO.manejoId())
                    .orElseThrow(() -> new RuntimeException("Manejo não encontrado"));
            itensManejo.setManejo(manejo);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repositorioItensManejo.save(itensManejo));
    }

    @DeleteMapping ("/itensmanejo/{id}")
    public ResponseEntity<Object> excluirItensManejo(@PathVariable(value="id") Integer id){
        Optional<ItensManejo> itensManejo = repositorioItensManejo.findById(id);
        if (itensManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ItensManejo de id "+id+" não existe.");
        }
        repositorioItensManejo.delete(itensManejo.get());
        return ResponseEntity.status(HttpStatus.OK).body("ItensManejo id "+id+" removido com sucesso!");
    }
}
