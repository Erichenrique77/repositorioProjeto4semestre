package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.ManejoDTO;
import br.com.fatec.apiCastracao.modelo.Manejo;
import br.com.fatec.apiCastracao.repository.ManejoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ManejoController {
    @Autowired
    ManejoRepository repositorioManejo;

    @PostMapping("/manejos")
    public ResponseEntity<Manejo> salvarManejo(@RequestBody ManejoDTO manejoDTO){
        var manejoModelo = new Manejo();
        BeanUtils.copyProperties(manejoDTO, manejoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioManejo.save(manejoModelo));
    }

    @GetMapping("/manejos")
    public ResponseEntity<List<Manejo>> getAllManejos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioManejo.findAll());
    }

    @GetMapping("/manejo/{id}")
    public ResponseEntity<Object> getManejoPorId(@PathVariable(value="id") Integer id){
        Optional<Manejo> manejo = repositorioManejo.findById(id);
        if (manejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manejo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(manejo.get());
    }

    @PutMapping ("/manejo/{id}")
    public ResponseEntity<Object> atualizarManejo(@PathVariable(value="id") Integer id,
                                                  @RequestBody ManejoDTO manejoDTO){
        Optional<Manejo> manejo = repositorioManejo.findById(id);
        if (manejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manejo de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(manejoDTO, manejo.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioManejo.save(manejo.get()));
    }

    @DeleteMapping ("/manejo/{id}")
    public ResponseEntity<Object> excluirManejo(@PathVariable(value="id") Integer id){
        Optional<Manejo> manejo = repositorioManejo.findById(id);
        if (manejo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manejo de id "+id+" não existe.");
        }
        repositorioManejo.delete(manejo.get());
        return ResponseEntity.status(HttpStatus.OK).body("Manejo id "+id+" removido com sucesso!");
    }
}
