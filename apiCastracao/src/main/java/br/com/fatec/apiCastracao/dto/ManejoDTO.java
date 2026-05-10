package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ManejoDTO(@NotBlank String nome, List<InsumosManejoDTO> insumos,
                        List<ItensManejoDTO> itensManejo) {
}
