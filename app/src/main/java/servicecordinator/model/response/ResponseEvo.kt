package servicecordinator.model.response

data class ResponseEvo(
    val mensaje: String,
    val objeto: String,
    val operacionExitosa: Boolean,
    val redirigir: Boolean
)