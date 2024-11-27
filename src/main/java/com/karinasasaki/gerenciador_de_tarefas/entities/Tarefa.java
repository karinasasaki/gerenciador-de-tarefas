package com.karinasasaki.gerenciador_de_tarefas.entities;

import com.karinasasaki.gerenciador_de_tarefas.entities.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tarefa")
@Data
public class Tarefa implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String titulo;

  private String descricao;

  private Integer status = 0;

  private final Calendar dataCriacao = Calendar.getInstance();

  private Calendar dataConclusao;

  public Tarefa() {}

  public Tarefa(String titulo, String descricao) {
    this.titulo = titulo;
    this.descricao = descricao;
  }

  public StatusTarefa getStatus() {
    return StatusTarefa.getStatus(status);
  }

  public void setStatus(StatusTarefa status) {
    if (status != null) {
      this.status = status.getCode();
    }
  }
}