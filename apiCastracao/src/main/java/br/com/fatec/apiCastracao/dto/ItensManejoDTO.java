package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotNull;

public record ItensManejoDTO(@NotNull Integer agendamentoId, @NotNull Integer manejoId) {
}
