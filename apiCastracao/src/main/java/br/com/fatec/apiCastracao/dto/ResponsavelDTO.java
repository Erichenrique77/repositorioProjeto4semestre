package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;

public record ResponsavelDTO(@NotBlank String nome, @NotBlank String telefone,
                             @NotBlank String tipo) {
}
