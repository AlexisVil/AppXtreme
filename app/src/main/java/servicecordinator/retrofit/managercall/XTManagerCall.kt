package servicecordinator.retrofit.managercall

import android.content.Context
import mx.com.evotae.appxtreme.framework.util.extensions.log
import retrofit2.HttpException
import retrofit2.Response
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResultApi
import servicecordinator.retrofit.model.exception.XTConectionException
import servicecordinator.retrofit.model.exception.XTValidationDefault
import servicecordinator.retrofit.model.inter.XTValidationCode
import java.lang.Exception

const val APP_NAME: String = "XtremeAPP"
const val USER_APP: String = "USER_APP"
const val PWD_APP : String = "PWD_APP"
const val OPERATOR_APP: String = "OPERATOR_APP"
private const val MESSAGE_ERROR_GENERAL: String =
    "Por el momento no es posible realizar esta operación, intentelo más tarde."
private const val MESSAGE_ERROR_GENERAL_NETWORK: String =
    "Ocurrió un error al intentar conectar con nuestros servidores."


open class XTManagerCall {
    private val TAG = "ManagerCall"
    suspend fun <T : Any> managerCallApi(
        call: suspend () -> Response<T>,
        validation: XTValidationCode<Response<T>>? = XTValidationDefault(),
        context: Context? = null
    ): XTResponseData<T?> {
        val result: XTResultApi<T> = safeApiResult(call, validation, context)
        val dataResponse: XTResponseData<T?> = XTResponseData()

        when(result){
            is XTResultApi.Succes -> {
                dataResponse.apply {
                    sucess = true
                    data = result.data
                }
            }
            is XTResultApi.Error -> {
                dataResponse.exception = result.exception
            }
        }
        return dataResponse
    }


    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        validation: XTValidationCode<Response<T>>? = XTValidationDefault(),
        context: Context? = null
    ): XTResultApi<T> {
        var exception: Exception? = null
        var data: T? = null
        try {
            val response = call.invoke()
            validation?.executeValidation(response)

            if (exception == null && response.isSuccessful) {
                data = response.body()
            }

        } catch (ex: XTConectionException) {
            "LOG-$TAG Connection Exception -> ${ex.message}".log()
            exception = Exception(ex.message)
        } catch (ex: HttpException) {
            "LOG-$TAG HTTP Exception -> ${ex.message}".log()
            exception = Exception(MESSAGE_ERROR_GENERAL_NETWORK)
        } catch (ex: Exception) {
            "LOG-$TAG Exception -> ${ex.message}".log()
            exception = Exception(MESSAGE_ERROR_GENERAL)

        }
        return exception?.let {
            XTResultApi.Error(it)
        }?:kotlin.run {
            XTResultApi.Succes(data)
        }
    }
}