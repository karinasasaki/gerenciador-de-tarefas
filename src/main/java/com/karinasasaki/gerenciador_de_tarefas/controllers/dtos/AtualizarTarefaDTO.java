package com.karinasasaki.gerenciador_de_tarefas.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Calendar;

@Schema(name = "Tarefa")
@Builder
public record AtualizarTarefaDTO(

    @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
    String titulo,
    @Size(max = 1500, message = "O tamanho do campo descricao excedeu 1500 caracteres")
    String descricao,
    String status,
    Calendar dataConclusao
) {
}