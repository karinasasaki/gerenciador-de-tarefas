package io.github.karinasasaki.gerenciadordetarefas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.karinasasaki.gerenciadordetarefas.entities.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Integer id;

  @NotBlank(message = "O campo titulo deve ser informado")
  @Size(max = 200, message = "O tamanho do campo titulo excedeu 200 caracteres")
  @Column(length = 200, nullable = false)
  private String titulo;

  @Size(max = 1500, message = "O tamanho do campo descricao excedeu 1500 caracteres")
  @Column(length = 1500)
  private String descricao;

  @NotBlank(message = "O campo status não deve ser nulo")
  @Builder.Default
  @Column(nullable = false)
  private String status = "PENDENTE";

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
  @Setter(AccessLevel.NONE)
  @Column(nullable = false, updatable = false)
  private final Instant dataCriacao = Instant.now();

  private Calendar dataConclusao;

  public Tarefa(String titulo, String descricao) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.status = "PENDENTE";

    anularDescricaoEmBranco();
    dataConclusaoDeveSerMaiorQueDataCriacao();
  }

  public void setStatus(StatusTarefa status) {
    if (status != null) {
      this.status = status.getCode();
    }
  }

  public void anularDescricaoEmBranco() {
    if (this.getDescricao() != null && this.getDescricao().isBlank()) {
      this.setDescricao(null);
    }
  }

  public void dataConclusaoDeveSerMaiorQueDataCriacao() {
    if (this.getDataConclusao() != null) {
      Instant dataConclusao = this.getDataConclusao().toInstant();
      if (dataConclusao.isBefore(this.getDataCriacao())) {
        throw new ValidationException("A dataConclusao deve ser posterior a dataCriacao");
      }
    }
  }
}