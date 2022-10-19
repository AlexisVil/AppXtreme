package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTPayServiceApi
import servicecordinator.apis.XTSellRechargeApi
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTPayServiceApiCall(private val context: Context) : XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTPayServiceApi>()
        .setHost(Routers.HOSTEVO)
        .setContext(context)
        .setClass(XTPayServiceApi::class.java)
        .builder().instance()

    suspend fun payService(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        email: String,
        id: String,
        numeroCuenta: String,
        montovar: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayService>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.payService(
                    idOperacion,
                    user,
                    pwd,
                    claveOperador,
                    regId,
                    email,
                    id,
                    numeroCuenta,
                    montovar
                ).await()
            }
        )
    }
}