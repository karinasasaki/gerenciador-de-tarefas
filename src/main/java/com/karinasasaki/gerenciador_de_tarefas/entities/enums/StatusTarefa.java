package com.karinasasaki.gerenciador_de_tarefas.entities.enums;

import java.util.Objects;

public enum StatusTarefa {

  PENDENTE(0),
  EM_ANDAMENTO(1),
  CONCLUIDA(2);

  private int code;

  StatusTarefa(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static StatusTarefa getStatus(int code) {
    for (StatusTarefa status: StatusTarefa.values()) {
      if (Objects.equals(status.getCode(), code)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Status inválido.");
  }
}
