package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.AnimalDTO;
import br.com.fatec.apiCastracao.modelo.Animal;
import br.com.fatec.apiCastracao.modelo.Tutor;
import br.com.fatec.apiCastracao.repository.AnimalRepository;
import br.com.fatec.apiCastracao.repository.TutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalController {
    @Autowired
    AnimalRepository repositorioAnimal;

    @Autowired
    TutorRepository repositorioTutor;

    @PostMapping("/animais")
    public ResponseEntity<Animal> salvarAnimal(@RequestBody AnimalDTO animalDTO){
        var animalModelo = new Animal();
        BeanUtils.copyProperties(animalDTO, animalModelo, "tutorId");

        if (animalDTO.tutorId() != null) { // Converte tutorId -> Tutor
            Tutor tutor = repositorioTutor.findById(animalDTO.tutorId())
                    .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
            animalModelo.setTutor(tutor);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repositorioAnimal.save(animalModelo));
    }


    @GetMapping("/animais")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioAnimal.findAll());
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<Object> getAnimalPorId(@PathVariable(value="id") Integer id){
        Optional<Animal> animal = repositorioAnimal.findById(id);
        if (animal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animal.get());
    }

    @PutMapping("/animal/{id}")
    public ResponseEntity<Object> atualizarAnimal(
            @PathVariable Integer id,
            @RequestBody AnimalDTO animalDTO) {

        Animal animal = repositorioAnimal.findById(id)
                .orElse(null);

        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Animal de id " + id + " não existe.");
        }

        BeanUtils.copyProperties(animalDTO, animal, "tutorId", "id");

        if (animalDTO.tutorId() != null) {
            Tutor tutor = repositorioTutor.findById(animalDTO.tutorId())
                    .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
            animal.setTutor(tutor);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(repositorioAnimal.save(animal));
    }


    @DeleteMapping ("/animal/{id}")
    public ResponseEntity<Object> excluirAnimal(@PathVariable(value="id") Integer id){
        Optional<Animal> animal = repositorioAnimal.findById(id);
        if (animal.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal de id "+id+" não existe.");
        }
        repositorioAnimal.delete(animal.get());
        return ResponseEntity.status(HttpStatus.OK).body("Animal id "+id+" removido com sucesso!");
    }
}
