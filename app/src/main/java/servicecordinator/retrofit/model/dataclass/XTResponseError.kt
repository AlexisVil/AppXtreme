package servicecordinator.retrofit.model.dataclass

import com.google.gson.annotations.SerializedName

class XTResponseError {
    @SerializedName("operacionExitosa")
    val operation: Boolean = true
    @SerializedName("redirigir")
    val redirect: Boolean = false
    @SerializedName("mensaje")
    val mensaje: String? = null
    @SerializedName("objeto")
    val objeto: String? = null

}