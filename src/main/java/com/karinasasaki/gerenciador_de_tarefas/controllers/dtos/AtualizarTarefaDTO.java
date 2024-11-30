package com.karinasasaki.gerenciador_de_tarefas.controllers.dtos;

import java.util.Calendar;

public record AtualizarTarefaDTO(
    String titulo,
    String descricao,
    String status,
    Calendar dataConclusao
) {
}