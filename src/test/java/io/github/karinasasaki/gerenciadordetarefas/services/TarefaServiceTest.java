package io.github.karinasasaki.gerenciadordetarefas.services;

import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.AtualizarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.CriarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.RetornoTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import io.github.karinasasaki.gerenciadordetarefas.repositories.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

  @Mock
  private TarefaRepository repository;

  @InjectMocks
  private TarefaService service;

  @Test
  void TarefaService_ListarTarefas_DeveRetornarListaVazia_QuandoNaoTiverValor() {
    Page<Tarefa> tarefas = new PageImpl<>(new ArrayList<>());

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(tarefas);

    Page<RetornoTarefaDTO> listaVazia = service.listarTarefas(0, 10);

    Assertions.assertNotNull(listaVazia);
    Assertions.assertFalse(listaVazia.hasContent());
  }

  @Test
  void TarefaService_ListarTarefas_DeveRetornarDuasTarefas_QuandoTiverDoisValores() {
    Tarefa tarefa1 = Tarefa.builder().build(),
        tarefa2 = Tarefa.builder().build();
    List<Tarefa> listaTarefas = new ArrayList<>(List.of(tarefa1, tarefa2));
    Page<Tarefa> tarefas = new PageImpl<>(listaTarefas);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(tarefas);

    Page<RetornoTarefaDTO> duasTarefas = service.listarTarefas(0, 10);

    Assertions.assertNotNull(duasTarefas);
    Assertions.assertEquals(2, duasTarefas.getTotalElements());
  }

  @Test
  void TarefaService_CriarTarefa_DeveRetornarTarefaCriada() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    RetornoTarefaDTO retornoEsperado = RetornoTarefaDTO.builder()
        .id(1)
        .titulo("Criar cartão")
        .descricao("Ajustar cor do botão")
        .status("PENDENTE")
        .dataCriacao(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(formatter))
        .dataConclusao(null).build();

    Tarefa tarefa = Tarefa.builder()
        .id(1)
        .titulo("Criar cartão")
        .descricao("Ajustar cor do botão")
        .status("PENDENTE")
        .dataConclusao(null).build();

    Mockito.when(repository.save(Mockito.any(Tarefa.class))).thenReturn(tarefa);

    CriarTarefaDTO dto = CriarTarefaDTO.builder()
        .titulo("Criar cartão")
        .descricao("Ajustar cor do botão")
        .dataConclusao(null).build();

    Tarefa tarefaCriada = service.criarTarefa(dto);
    RetornoTarefaDTO retornoTarefaDTO = RetornoTarefaDTO.fromEntity(tarefaCriada);

    Assertions.assertNotNull(retornoTarefaDTO);
    Assertions.assertEquals(retornoEsperado, retornoTarefaDTO);
  }

  @Test
  void TarefaService_AtualizarTarefa_DeveRetornarTarefaAtualizada() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    RetornoTarefaDTO retornoEsperado = RetornoTarefaDTO.builder()
        .id(1)
        .titulo("Avaliar PR")
        .descricao(null)
        .status("EM_ANDAMENTO")
        .dataCriacao(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(formatter))
        .dataConclusao(null).build();

    Tarefa tarefa = Tarefa.builder()
        .id(1)
        .titulo("Avaliar PR")
        .descricao(null)
        .status("EM_ANDAMENTO")
        .dataConclusao(null).build();

    Tarefa tarefaDesatualizada = Tarefa.builder()
        .id(1)
        .titulo("Avaliar PR")
        .descricao(null)
        .status("PENDENTE")
        .dataConclusao(null).build();

    Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(tarefaDesatualizada));
    Mockito.when(repository.save(Mockito.any(Tarefa.class))).thenReturn(tarefa);

    AtualizarTarefaDTO dto = AtualizarTarefaDTO.builder()
        .status("EM_ANDAMENTO").build();

    Tarefa tarefaAtualizada = service.atualizarTarefa(1, dto);
    RetornoTarefaDTO retornoTarefaDTO = RetornoTarefaDTO.fromEntity(tarefaAtualizada);

    Assertions.assertNotNull(tarefaAtualizada);
    Assertions.assertEquals(retornoEsperado, retornoTarefaDTO);
  }

  @Test
  void TarefaService_AtualizarTarefaComIdInexistente_DeveRetornarIllegalArgumentException() {
    Mockito.when(repository.findById(0)).thenReturn(Optional.empty());

    AtualizarTarefaDTO dto = AtualizarTarefaDTO.builder()
        .status("EM_ANDAMENTO").build();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.atualizarTarefa(0, dto);
    });
  }
}