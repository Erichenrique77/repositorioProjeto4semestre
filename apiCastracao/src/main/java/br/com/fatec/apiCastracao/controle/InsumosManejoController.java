package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.InsumosManejoDTO;
import br.com.fatec.apiCastracao.modelo.*;
import br.com.fatec.apiCastracao.repository.InsumoRepository;
import br.com.fatec.apiCastracao.repository.InsumosManejoRepository;
import br.com.fatec.apiCastracao.repository.ManejoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InsumosManejoController {
    @Autowired
    InsumosManejoRepository repositorioInsumosManejo;

    @Autowired
    InsumoRepository repositorioInsumo;

    @Autowired
    ManejoRepository repositorioManejo;

    @PostMapping("/insumosmanejo")
    public ResponseEntity<InsumosManejo> salvarInsumosManejo(@RequestBody InsumosManejoDTO dto){
        var insumosManejoModelo = new InsumosManejo();
        BeanUtils.copyProperties(dto, insumosManejoModelo, "insumoId", "manejoId");

        // Relacionamento: Insumo
        if (dto.insumoId() != null) {
            Insumo insumo = repositorioInsumo.findById(dto.insumoId())
                    .orElseThrow(() -> new RuntimeException("Insumo não encontrado"));
            insumosManejoModelo.setInsumo(insumo);
        }

        // Relacionamento: Manejo
        if (dto.manejoId() != null) {
            Manejo manejo = repositorioManejo.findById(dto.manejoId())
                    .orElseThrow(() -> new RuntimeException("Manejo não encontrado"));
            insumosManejoModelo.setManejo(manejo);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repositorioInsumosManejo.save(insumosManejoModelo));
    }

    @GetMapping("/insumosmanejo")
    public ResponseEntity<List<InsumosManejo>> getAllInsumosManejos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioInsumosManejo.findAll());
    }

    @GetMapping("/insumosmanejo/{id}")
    public ResponseEntity<Object> getInsumosManejoPorId(@PathVariable(value="id") Integer id){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(insumosManejo.get());
    }

    @PutMapping("/insumosmanejo/{id}")
    public ResponseEntity<Object> atualizarInsumosManejo(@PathVariable(value="id") Integer id,
                                                       @RequestBody InsumosManejoDTO insumosManejoDTO){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(insumosManejoDTO, insumosManejo.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioInsumosManejo.save(insumosManejo.get()));
    }

    @DeleteMapping ("/insumosmanejo/{id}")
    public ResponseEntity<Object> excluirInsumosManejo(@PathVariable(value="id") Integer id){
        Optional<InsumosManejo> insumosManejo = repositorioInsumosManejo.findById(id);
        if (insumosManejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InsumosManejo de id "+id+" não existe.");
        }
        repositorioInsumosManejo.delete(insumosManejo.get());
        return ResponseEntity.status(HttpStatus.OK).body("InsumosManejo id "+id+" removido com sucesso!");
    }
}
