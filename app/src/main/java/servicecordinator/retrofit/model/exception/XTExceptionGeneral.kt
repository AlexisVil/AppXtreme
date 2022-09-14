package servicecordinator.retrofit.model.exception

import okio.IOException
import servicecordinator.retrofit.model.dataclass.XTResponseError

class XTExceptionGeneral constructor(private val xtResponseErrorCustom: XTResponseError): IOException() {
    val xtResponseError: XTResponseError?
    get() = xtResponseErrorCustom
}