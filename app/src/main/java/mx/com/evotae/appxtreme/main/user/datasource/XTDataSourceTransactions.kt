package mx.com.evotae.appxtreme.main.user.datasource

import servicecordinator.callapis.XTTransactionsApiCall
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceTransactions(private val callApi: XTTransactionsApiCall) {
    suspend fun lastTransactions(idOperacion: String,user: String, pwd: String, claveOperador: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?> {
        return callApi.lastTransactions(idOperacion, user, pwd, claveOperador)
    }
}