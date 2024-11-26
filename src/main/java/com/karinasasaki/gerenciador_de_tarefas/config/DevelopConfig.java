package com.karinasasaki.gerenciador_de_tarefas.config;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("develop")
public class DevelopConfig implements CommandLineRunner {

  @Autowired
  private TarefaRepository tarefaRepository;

  @Override
  public void run(String... args) throws Exception {
    Tarefa exemplo = new Tarefa("Refatorar o código", "Remover linhas desnecessárias");
    tarefaRepository.save(exemplo);
  }
}