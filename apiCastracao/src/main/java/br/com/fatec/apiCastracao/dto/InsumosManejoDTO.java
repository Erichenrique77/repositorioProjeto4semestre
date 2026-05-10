package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotNull;

public record InsumosManejoDTO(@NotNull Integer insumoId, @NotNull Integer manejoId,
                               @NotNull int qtde) {
}
