package com.karinasasaki.gerenciador_de_tarefas.entities.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum StatusTarefa {

  PENDENTE("PENDENTE"),
  EM_ANDAMENTO("EM_ANDAMENTO"),
  CONCLUIDA("CONCLUIDA");

  private final String code;

  StatusTarefa(String code) {
    this.code = code;
  }

  public static StatusTarefa getStatus(String code) {
    for (StatusTarefa status: StatusTarefa.values()) {
      if (Objects.equals(status.getCode(), code)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Status inválido");
  }
}