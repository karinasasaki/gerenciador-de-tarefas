package com.karinasasaki.gerenciador_de_tarefas.entities;

import com.karinasasaki.gerenciador_de_tarefas.entities.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tarefa")
@Data
@NoArgsConstructor
public class Tarefa implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "O campo título deve ser informado")
  @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
  private String titulo;

  @Size(max = 1500, message = "O tamanho do campo titulo excedeu 1500 caracteres")
  private String descricao;

  private Integer status = 0;

  private final Calendar dataCriacao = Calendar.getInstance();

  private Calendar dataConclusao;

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