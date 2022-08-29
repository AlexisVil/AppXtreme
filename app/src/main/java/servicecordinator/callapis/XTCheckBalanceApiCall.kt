package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTCheckBalanceApi
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTCheckBalanceApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTCheckBalanceApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTCheckBalanceApi::class.java)
        .builder().instance()
    suspend fun checkBalance(idOperacion: String, fima: String): XTResponseData<XTResponseGeneral<XTResponseCheckBalance>?>
    {
        return managerCallApi(
            context = context,
            call = {
                callApi.checkBalance(idOperacion, fima).await()
            }
        )
    }
}