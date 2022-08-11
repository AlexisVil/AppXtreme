package servicecordinator.retrofit.model.dataclass

import com.google.gson.annotations.SerializedName

class XTResponseGeneral<T>(
    @SerializedName("operacionExitosa") //Nombre del JSON lo toma para tomar el valor :serialized
    val operation: Boolean = false,
    @SerializedName("objeto")
    val result: T? = null
)