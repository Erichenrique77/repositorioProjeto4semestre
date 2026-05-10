package br.com.fatec.apiCastracao.controle;


import br.com.fatec.apiCastracao.dto.TutorDTO;
import br.com.fatec.apiCastracao.modelo.Tutor;
import br.com.fatec.apiCastracao.repository.TutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TutorController {
    @Autowired
    TutorRepository repositorioTutor;

    @PostMapping("/tutores")
    public ResponseEntity<Tutor> salvarTutor(@RequestBody TutorDTO tutorDTO){
        var tutorModelo = new Tutor();
        BeanUtils.copyProperties(tutorDTO, tutorModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioTutor.save(tutorModelo));
    }

    @GetMapping("/tutores")
    public ResponseEntity<List<Tutor>> getAllTutors(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioTutor.findAll());
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<Object> getTutorPorId(@PathVariable(value="id") Integer id){
        Optional<Tutor> tutor = repositorioTutor.findById(id);
        if (tutor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutor de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tutor.get());
    }

    @PutMapping ("/tutor/{id}")
    public ResponseEntity<Object> atualizarTutor(@PathVariable(value="id") Integer id,
                                                  @RequestBody TutorDTO tutorDTO){
        Optional<Tutor> tutor = repositorioTutor.findById(id);
        if (tutor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutor de id "+id+" não existe.");
        }
        BeanUtils.copyProperties(tutorDTO, tutor.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioTutor.save(tutor.get()));
    }

    @DeleteMapping ("/tutor/{id}")
    public ResponseEntity<Object> excluirTutor(@PathVariable(value="id") Integer id){
        Optional<Tutor> tutor = repositorioTutor.findById(id);
        if (tutor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutor de id "+id+" não existe.");
        }
        repositorioTutor.delete(tutor.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tutor id "+id+" removido com sucesso!");
    }
}
