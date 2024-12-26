package io.github.karinasasaki.gerenciadordetarefas.controllers.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Data
public class ErroResposta implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private Instant timestamp = Instant.now();
  private Integer status;
  private String error;
  private String path;

  public ErroResposta(Integer status, String error, String path) {
    this.status = status;
    this.error = error;
    this.path = path;
  }
}