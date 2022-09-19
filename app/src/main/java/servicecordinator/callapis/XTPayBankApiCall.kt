package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTPayBankApi
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTPayBankApiCall(private val context: Context) : XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTPayBankApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTPayBankApi::class.java)
        .builder().instance()

    suspend fun payBank(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        banco: String,
        tipoDeposito: String,
        sucursal: String,
        referencia: String,
        fecha: String,
        hora: String,
        minuto: String,
        monto: String,
        recargas: String,
        servicios: String,
        comentarioView: String,
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.postPayBank(
                    idOperacion,
                    user,
                    pwd,
                    claveOperador,
                    banco,
                    tipoDeposito,
                    sucursal,
                    referencia,
                    fecha,
                    hora,
                    minuto,
                    monto,
                    recargas,
                    servicios,
                    comentarioView,
                    regid
                ).await()
            }
        )
    }
}