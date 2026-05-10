package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.ResponsavelDTO;
import br.com.fatec.apiCastracao.modelo.Responsavel;
import br.com.fatec.apiCastracao.repository.ResponsavelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ResponsavelController {
    @Autowired
    ResponsavelRepository repositorioResponsavel;

    @PostMapping("/responsaveis")
    public ResponseEntity<Responsavel> salvarResponsavel(@RequestBody ResponsavelDTO responsavelDTO){
        var responsavelModelo = new Responsavel();
        BeanUtils.copyProperties(responsavelDTO, responsavelModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioResponsavel.save(responsavelModelo));
    }

    @GetMapping("/responsaveis")
    public ResponseEntity<List<Responsavel>> getAllResponsavels(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioResponsavel.findAll());
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity<Object> getResponsavelPorId(@PathVariable(value="id") Integer id){
        Optional<Responsavel> responsavel = repositorioResponsavel.findById(id);
        if (responsavel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responsavel.get());
    }

    @PutMapping ("/responsavel/{id}")
    public ResponseEntity<Object> atualizarResponsavel(@PathVariable(value="id") Integer id,
                                                 @RequestBody ResponsavelDTO responsavelDTO){
        Optional<Responsavel> responsavel = repositorioResponsavel.findById(id);
        if (responsavel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(responsavelDTO, responsavel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioResponsavel.save(responsavel.get()));
    }

    @DeleteMapping ("/responsavel/{id}")
    public ResponseEntity<Object> excluirResponsavel(@PathVariable(value="id") Integer id){
        Optional<Responsavel> responsavel = repositorioResponsavel.findById(id);
        if (responsavel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel de id "+id+" não existe.");
        }
        repositorioResponsavel.delete(responsavel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Responsavel id "+id+" removido com sucesso!");
    }
}
