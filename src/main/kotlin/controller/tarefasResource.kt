package controller

import dto.TarefaDTO
import model.Tarefa
import service.TarefaService
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
class TarefasResource {
    @Inject
    lateinit var tarefasService: TarefaService

    @GET
    fun listarTarefas(): List<Tarefa> = tarefasService.listarTarefas()

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    fun createTarefa(@Valid tarefaDTO: TarefaDTO): Response {
        val tarefas = Tarefa(
            titulo = tarefaDTO.titulo,
            descricao = tarefaDTO.descricao ?: "",
            data_inicio = tarefaDTO.data_inicio,
            prazo_conclusao = tarefaDTO.prazo_conclusao,
            status = tarefaDTO.status
        )


        tarefasService.criarTarefa(tarefas)
        return Response.status(Response.Status.CREATED).entity(tarefas).build()
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateTarefa(@PathParam("id") id: Long, @Valid tarefaDTO: TarefaDTO): Response {
        val tarefa = tarefasService.atualizarTarefa(id, tarefaDTO)
        return if (tarefa != null) {
            Response.ok(tarefa).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deleteTarefa(@PathParam("id") id: Long): Response {
        return if (tarefasService.removerTarefa(id)) {
            Response.noContent().build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}
