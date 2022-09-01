package mx.com.evotae.appxtreme.main.user.datasource

import servicecordinator.callapis.XTCheckBalanceApiCall
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceCheckBalance(private val callApi: XTCheckBalanceApiCall) {
    suspend fun checkBalance(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseCheckBalance>>?>{
        return callApi.checkBalance(idOperacion, firma)
    }
}