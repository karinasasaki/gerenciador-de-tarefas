package io.github.karinasasaki.gerenciadordetarefas.repositories;

import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}