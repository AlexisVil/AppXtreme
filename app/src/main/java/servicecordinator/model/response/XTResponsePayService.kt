package servicecordinator.model.response

data class XTResponsePayService(
    val autorizacionTelcel: String,
    val fecha: String,
    val monto: String,
    val telefono: String,
    val ticket: String
)