package servicecordinator.retrofit.model.exception

import java.io.IOException

class XTConectionException(private val messageCustom: String? = null) : IOException() {
    override val message: String?
        get() = messageCustom ?: "Error de conexión."
}