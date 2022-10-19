package servicecordinator.retrofit.model.exception

import retrofit2.Response
import servicecordinator.retrofit.model.inter.XTValidationCode
import java.lang.Exception

class XTValidationDefault<T>: XTValidationCode<Response<T>> {
    override fun executeValidation(response: Response<T>) {
        if (!response.code().toString().contains("20"))
            throw Exception(response.message())
    }
}