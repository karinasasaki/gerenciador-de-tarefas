package com.karinasasaki.gerenciador_de_tarefas.entities;

import com.karinasasaki.gerenciador_de_tarefas.entities.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Tarefa implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Integer id;

  @NotBlank(message = "O campo título deve ser informado")
  @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
  @Column(length = 200, nullable = false)
  private String titulo;

  @Size(max = 1500, message = "O tamanho do campo titulo excedeu 1500 caracteres")
  @Column(length = 1500)
  private String descricao;

  @Column(nullable = false)
  private Integer status = 0;

  @Column(nullable = false)
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