package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MovimentacaoDTO(@NotBlank String origem, @NotBlank Date data,
                              @NotBlank int quantidade, @NotNull Integer insumoId) {
}
