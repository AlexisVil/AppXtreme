package servicecordinator.model.response

data class XTResponseVentaRecarga(
    val mensaje: String?,
    var objeto: String?,
    val operacionExitosa: Boolean?,
    val redirigir: Boolean?
)
