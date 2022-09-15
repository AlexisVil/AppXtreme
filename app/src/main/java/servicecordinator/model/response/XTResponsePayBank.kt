package servicecordinator.model.response

data class XTResponsePayBank(
    val mensaje: String?,
    val objeto: String?,
    val operacionExitosa: Boolean?,
    val redirigir: Boolean?
)