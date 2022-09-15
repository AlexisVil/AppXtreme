package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTBankDepositApi
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTBankDepositApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTBankDepositApi>()
        .setContext(context)
        .setClass(XTBankDepositApi::class.java)
        .setHost(Routers.HOST)
        .builder().instance()

    suspend fun bankDeposit(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?>
    {
        return managerCallApi(
            context = context,
            call = {
                callApi.getBankDeposit(idOperacion).await()
            }
        )
    }
}