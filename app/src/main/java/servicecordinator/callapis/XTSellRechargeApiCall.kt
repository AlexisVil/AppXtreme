package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTSellRechargeApi
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTSellRechargeApiCall(private val context: Context) : XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTSellRechargeApi>()
        .setHost(Routers.HOST)
        .setContext(context)
        .setClass(XTSellRechargeApi::class.java)
        .builder().instance()

    suspend fun sellRecharge(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        versionCode: String,
        id: String,
        numeroCelular: String
    ): XTResponseData<XTResponseGeneral<XTResponseSellRecharge>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.sellReacharge(
                    idOperacion,
                    user,
                    pwd,
                    claveOperador,
                    regId,
                    versionCode,
                    id,
                    numeroCelular
                ).await()
            }
        )
    }
}