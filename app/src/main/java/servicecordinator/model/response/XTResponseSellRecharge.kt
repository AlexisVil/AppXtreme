package servicecordinator.model.response

data class XTResponseSellRecharge(
    val autorizacionTelcel: String,
    val fecha: String,
    val monto: String,
    val telefono: String,
    val ticket: String
)