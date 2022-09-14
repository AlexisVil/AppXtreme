package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTLoginApi
import servicecordinator.apis.XTTransactionsApi
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTTransactionsApiCall(private val context: Context) : XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTTransactionsApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTTransactionsApi::class.java)
        .builder().instance()

    suspend fun lastTransactions(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String
    ): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.getlastTransactions(idOperacion, user, pwd, claveOperador).await()
            }
        )
    }
}