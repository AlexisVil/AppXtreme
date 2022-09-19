package servicecordinator.retrofit.model.exception

import retrofit2.Response
import servicecordinator.retrofit.model.dataclass.XTResponseError
import servicecordinator.retrofit.model.inter.XTValidationCode
import java.lang.Exception

class XTValidationDefault<T> : XTValidationCode<Response<T>> {
    override fun executeValidation(response: Response<T>) {
        if (!response.code().toString().contains("20"))
            //throw Exception(response.message())
            //throw XTExceptionGeneral(response.body() as XTResponseError)
        try {
            var mensaje = response.body() as XTResponseError
            XTExceptionGeneral(mensaje)
            println(mensaje.mensaje.toString())
        } catch (e: XTExceptionGeneral) {
            println(e.message)
            throw XTExceptionGeneral(response.body() as XTResponseError)
        }
    }
}