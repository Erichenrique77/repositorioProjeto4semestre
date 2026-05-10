package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;


import java.util.Date;

public record AgendamentoDTO(String observacoes, @NotBlank Date dataPrevista,
                             @NotBlank Integer animalId, @NotBlank Integer responsavelId) {
}
