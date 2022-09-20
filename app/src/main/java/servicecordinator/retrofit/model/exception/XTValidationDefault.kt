package servicecordinator.retrofit.model.exception

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import servicecordinator.retrofit.model.dataclass.XTResponseError
import servicecordinator.retrofit.model.inter.XTValidationCode
import java.lang.Exception

class XTValidationDefault<T> : XTValidationCode<Response<T>> {
    override fun executeValidation(response: Response<T>) {
        if (!response.code().toString().contains("20"))
            throw Exception(response.message())
        val gson = Gson()
        val type = object: TypeToken<XTResponseError>() {}.type
        val errorResponse: XTResponseError = gson.fromJson(response.body().toString(), type)
        //throw XTExceptionGeneral(errorResponse)
    }
}