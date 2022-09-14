package servicecordinator.retrofit.model.dataclass

import servicecordinator.retrofit.model.exception.XTExceptionGeneral
import java.lang.Exception

data class XTResponseData<T>(
    var sucess: Boolean = false,
    var data: T? = null,
    var exception: XTExceptionGeneral? = null
)