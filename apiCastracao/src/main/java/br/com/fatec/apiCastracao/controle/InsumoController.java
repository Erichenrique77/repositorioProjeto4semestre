package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.InsumoDTO;
import br.com.fatec.apiCastracao.modelo.Insumo;
import br.com.fatec.apiCastracao.repository.InsumoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InsumoController {
    @Autowired
    InsumoRepository repositorioInsumo;

    @PostMapping("/insumos")
    public ResponseEntity<Insumo> salvarInsumo(@RequestBody InsumoDTO insumoDTO){
        var insumoModelo = new Insumo();
        BeanUtils.copyProperties(insumoDTO, insumoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioInsumo.save(insumoModelo));
    }

    @GetMapping("/insumos")
    public ResponseEntity<List<Insumo>> getAllInsumos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioInsumo.findAll());
    }

    @GetMapping("/insumo/{id}")
    public ResponseEntity<Object> getInsumoPorId(@PathVariable(value="id") Integer id){
        Optional<Insumo> insumo = repositorioInsumo.findById(id);
        if (insumo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(insumo.get());
    }

    @PutMapping ("/insumo/{id}")
    public ResponseEntity<Object> atualizarInsumo(@PathVariable(value="id") Integer id,
                                                  @RequestBody InsumoDTO insumoDTO){
        Optional<Insumo> insumo = repositorioInsumo.findById(id);
        if (insumo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(insumoDTO, insumo.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioInsumo.save(insumo.get()));
    }

    @DeleteMapping ("/insumo/{id}")
    public ResponseEntity<Object> excluirInsumo(@PathVariable(value="id") Integer id){
        Optional<Insumo> insumo = repositorioInsumo.findById(id);
        if (insumo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Insumo de id "+id+" não existe.");
        }
        repositorioInsumo.delete(insumo.get());
        return ResponseEntity.status(HttpStatus.OK).body("Insumo id "+id+" removido com sucesso!");
    }
}
