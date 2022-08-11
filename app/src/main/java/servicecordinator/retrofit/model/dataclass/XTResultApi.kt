package servicecordinator.retrofit.model.dataclass

import java.lang.Exception

sealed class XTResultApi<out T : Any> {
    data class Succes<out T : Any>(val data: T?): XTResultApi<T>()
    data class Error(val exception: Exception): XTResultApi<Nothing>()
}