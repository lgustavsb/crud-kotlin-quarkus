package service

import dto.TarefaDTO
import model.Tarefa
import repository.TarefaRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class TarefaService {
    @Inject
    private lateinit var tarefaRepository: TarefaRepository

    fun listarTarefas(): List<Tarefa> {
        val tarefas = tarefaRepository.listAll()
        return tarefas
    }


    @Transactional
    fun criarTarefa(tarefa: Tarefa): Tarefa {
        tarefaRepository.persist(tarefa)
        return tarefa
    }

    fun buscarTarefaPorId(id: Long): Tarefa? {
        return tarefaRepository.findById(id)
    }

    @Transactional
    fun atualizarTarefa(id: Long, tarefaDTO: TarefaDTO): Tarefa? {
        val tarefa = buscarTarefaPorId(id)
        return if (tarefa != null) {
            tarefa.titulo = tarefaDTO.titulo
            tarefa.descricao = tarefaDTO.descricao ?: ""
            tarefa.data_inicio = tarefaDTO.data_inicio
            tarefa.prazo_conclusao = tarefaDTO.prazo_conclusao
            tarefa.status = tarefaDTO.status
            tarefa
        } else {
            null
        }
    }

    @Transactional
    fun removerTarefa(id: Long): Boolean {
        val tarefa = buscarTarefaPorId(id)
        return if (tarefa != null) {
            tarefaRepository.delete(tarefa)
            true
        } else {
            false
        }
    }
}
