package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InsumoDTO(@NotBlank String nome, @NotNull String tipo,
                        @NotBlank double precoCusto, int qtdeEstoque,
                        List<MovimentacaoDTO> movimentacoes) {
}
