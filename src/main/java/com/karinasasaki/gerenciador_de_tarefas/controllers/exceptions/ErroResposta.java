package com.karinasasaki.gerenciador_de_tarefas.controllers.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;

@NoArgsConstructor
@Data
public class ErroResposta implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private Calendar timestamp = Calendar.getInstance();
  private Integer status;
  private String error;
  private String path;

  public ErroResposta(Integer status, String error, String path) {
    this.status = status;
    this.error = error;
    this.path = path;
  }
}