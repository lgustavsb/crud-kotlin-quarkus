package model

import enums.StatusTarefa
import java.time.LocalDate
import javax.persistence.*

@Entity
class Tarefa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var titulo: String,
    var descricao: String,
    var data_inicio: LocalDate,
    var prazo_conclusao: LocalDate,
    var status: StatusTarefa
) {
    constructor() : this(
        id = null,
        titulo = "",
        descricao = "",
        data_inicio = LocalDate.now(),
        prazo_conclusao = LocalDate.now().plusDays(1),
        status = StatusTarefa.PENDENTE
    )
}

