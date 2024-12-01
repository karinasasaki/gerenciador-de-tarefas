package com.karinasasaki.gerenciadordetarefas.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Calendar;

@Schema(name = "Tarefa")
@Builder
public record AtualizarTarefaDTO(
    @NotBlank(message = "O campo titulo deve ser informado")
    @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
    String titulo,
    @Size(max = 1500, message = "O tamanho do campo descricao excedeu 1500 caracteres")
    String descricao,
    String status,
    Calendar dataConclusao
) {
}