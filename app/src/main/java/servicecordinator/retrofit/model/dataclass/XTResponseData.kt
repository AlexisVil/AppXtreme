package servicecordinator.retrofit.model.dataclass

import java.lang.Exception

data class XTResponseData<T>(
    var sucess: Boolean = false,
    var data: T? = null,
    var exception: Exception? = null
)