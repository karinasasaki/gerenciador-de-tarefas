package io.github.karinasasaki.gerenciadordetarefas.controllers.dtos;

import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import io.github.karinasasaki.gerenciadordetarefas.entities.enums.StatusTarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;

@Schema(name = "Tarefa")
@Builder
public record CriarTarefaDTO(
    @NotBlank(message = "O campo titulo deve ser informado")
    @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
    String titulo,
    @Size(max = 1500, message = "O tamanho do campo descricao excedeu 1500 caracteres")
    String descricao,
    String status,
    Instant dataConclusao
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