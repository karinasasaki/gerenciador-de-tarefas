package com.karinasasaki.gerenciador_de_tarefas.config;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("develop")
@OpenAPIDefinition(
    info = @Info(
        title = "Gerenciador de tarefas",
        description = "API para Gerenciamento de Tarefas.",
        version = "1.0",
        contact = @Contact(
            name = "Karina Sasaki",
            email = "karinasasaki99@gmail.com",
            url = "https://github.com/karinasasaki/gerenciador-de-tarefas"
        )
    )
)
public class DevelopConfig implements CommandLineRunner {

  @Autowired
  private TarefaRepository tarefaRepository;

  @Override
  public void run(String... args) {
    Tarefa exemplo = new Tarefa("Refatorar o código", "Remover linhas desnecessárias");
    tarefaRepository.save(exemplo);
  }
}