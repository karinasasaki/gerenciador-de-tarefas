package com.karinasasaki.gerenciador_de_tarefas;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.entities.enums.StatusTarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TarefaTest {

  @Autowired
  TarefaRepository repository;

  @Test
  public void deveAdicionarTarefa() {
    Tarefa tarefa = new Tarefa();
    tarefa.setTitulo("Avaliar os PRs pendentes");
    tarefa = repository.save(tarefa);
    System.out.println("Tarefa adicionada: " + tarefa);
  }

  @Test
  public void deveAtualizarTarefa() {
    Optional<Tarefa> tarefaExistente = repository.findById(1);
    if (tarefaExistente.isPresent()) {
      System.out.println("Tarefa existente: " + tarefaExistente);

      Tarefa tarefaAtualizada = tarefaExistente.get();
      tarefaAtualizada.setStatus(StatusTarefa.EM_ANDAMENTO);
      tarefaAtualizada.setTitulo("Limpar o código");
      tarefaAtualizada = repository.save(tarefaAtualizada);
      System.out.println("Tarefa atualizada: " + tarefaAtualizada);
    }
  }
}