package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTSimSellApi
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTSimSellApiCall(private val context: Context) : XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTSimSellApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTSimSellApi::class.java)
        .builder().instance()

    suspend fun simSell(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regid: String,
        versionCode: String,
        id: String,
        numeroCelular: String,
        montovar: String
    ): XTResponseData<XTResponseGeneral<XTResponseSimSell>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.postSimSell(
                    idOperacion,
                    user,
                    pwd,
                    claveOperador,
                    regid,
                    versionCode,
                    id,
                    numeroCelular,
                    montovar
                ).await()
            }
        )
    }
}