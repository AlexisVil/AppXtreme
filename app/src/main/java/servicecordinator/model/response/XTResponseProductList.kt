package servicecordinator.model.response

data class XTResponseProductList(
    val carrier: String,
    val descripcion: String,
    val expresionRegularErr: String,
    val fee_total: Double,
    val format_account: String,
    val formulario: String,
    val id: String,
    val monto: Double,
    val tipo_servicio: String
)