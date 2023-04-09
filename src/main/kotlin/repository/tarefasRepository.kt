package repository


import io.quarkus.hibernate.orm.panache.PanacheRepository
import model.Tarefa
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TarefaRepository : PanacheRepository<Tarefa>