package com.karinasasaki.gerenciador_de_tarefas.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Calendar;

@Schema(name = "Tarefa")
@Builder
public record AtualizarTarefaDTO(
    String titulo,
    String descricao,
    String status,
    Calendar dataConclusao
) {
}