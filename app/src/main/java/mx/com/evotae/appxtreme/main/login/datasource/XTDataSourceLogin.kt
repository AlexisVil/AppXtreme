package mx.com.evotae.appxtreme.main.login.datasource

import servicecordinator.callapis.XTLoginApiCall
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceLogin(private val callApi: XTLoginApiCall) {
    suspend fun login(): XTResponseData<XTResponseGeneral<XTResponseLogin>?>{
        return callApi.login()
    }
}