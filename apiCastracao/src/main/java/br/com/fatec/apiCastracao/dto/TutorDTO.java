package br.com.fatec.apiCastracao.dto;

import br.com.fatec.apiCastracao.modelo.Animal;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TutorDTO(@NotBlank String nome, @NotBlank String endereco,
                       @NotBlank String celular, List<AnimalDTO> animais) {
}
