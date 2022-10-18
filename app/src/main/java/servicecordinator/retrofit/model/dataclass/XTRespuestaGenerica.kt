package servicecordinator.retrofit.model.dataclass

import com.google.gson.annotations.SerializedName

class XTRespuestaGenerica<T>(
    val operacionExitosa: Boolean,
    val redirigir: Boolean,
    val mensaje: String?,
    val objeto: T?
)
