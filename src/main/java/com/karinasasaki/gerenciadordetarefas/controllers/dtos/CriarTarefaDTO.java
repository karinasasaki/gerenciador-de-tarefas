package com.karinasasaki.gerenciadordetarefas.controllers.dtos;

import com.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import com.karinasasaki.gerenciadordetarefas.entities.enums.StatusTarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Calendar;

@Schema(name = "Tarefa")
@Builder
public record CriarTarefaDTO(
    String titulo,
    String descricao,
    String status,
    Calendar dataConclusao
) {

  public Tarefa mapParaTarefaEntidade() {
    Tarefa tarefa = new Tarefa();

    tarefa.setTitulo(this.titulo);

    tarefa.setDescricao(this.descricao);

    if (this.status != null)
      tarefa.setStatus(StatusTarefa.getStatus(this.status));

    tarefa.setDataConclusao(this.dataConclusao);
    
    return tarefa;
  }
}