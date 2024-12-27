package io.github.karinasasaki.gerenciadordetarefas.controllers.dtos;

import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Builder
public record RetornoTarefaDTO(
    Integer id,
    String titulo,
    String descricao,
    String status,
    String dataCriacao,
    String dataConclusao
) {

  private static String formatarInstant(Instant data) {
    if (data == null)
      return null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return LocalDateTime.ofInstant(data, ZoneId.systemDefault()).format(formatter);
  }

  public static RetornoTarefaDTO fromEntity(Tarefa tarefa) {
    return new RetornoTarefaDTO(
        tarefa.getId(),
        tarefa.getTitulo(),
        tarefa.getDescricao(),
        tarefa.getStatus(),
        formatarInstant(tarefa.getDataCriacao()),
        formatarInstant(tarefa.getDataConclusao())
    );
  }
}