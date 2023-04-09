package dto

import enums.StatusTarefa
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TarefaDTO(

    @field:NotBlank(message = "O título é obrigatório")
    var titulo: String = "",

    var descricao: String? = null,

    @field:NotNull(message = "A data de início é obrigatória")
    var data_inicio: LocalDate = LocalDate.MIN,

    @field:NotNull(message = "O prazo de conclusão é obrigatório")
    var prazo_conclusao: LocalDate = LocalDate.MIN,

    @field:NotNull(message = "O status é obrigatório")
    var status: StatusTarefa = StatusTarefa.PENDENTE
) {
    constructor() : this(
        "", null, LocalDate.MIN, LocalDate.MIN, StatusTarefa.PENDENTE
    )
}

