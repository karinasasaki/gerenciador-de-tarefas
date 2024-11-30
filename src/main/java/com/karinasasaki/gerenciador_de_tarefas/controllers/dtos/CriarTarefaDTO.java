package com.karinasasaki.gerenciador_de_tarefas.controllers.dtos;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Calendar;

@Schema(name = "Tarefa")
public record CriarTarefaDTO(
    String titulo,
    String descricao,
    Calendar dataConclusao
) {

  public Tarefa mapParaTarefaEntidade() {
    Tarefa tarefa = new Tarefa();
    tarefa.setTitulo(this.titulo);
    tarefa.setDescricao(this.descricao);
    tarefa.setDataConclusao(this.dataConclusao);
    return tarefa;
  }
}