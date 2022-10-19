package servicecordinator.retrofit.model.dataclass

sealed class XTResultApi<out T : Any> {
    data class Succes<out T : Any>(val data: T?): XTResultApi<T>()
    data class Error(val exception: Exception): XTResultApi<Nothing>()
}