package mx.com.evotae.appxtreme.main.reportarpago.datasource

import servicecordinator.callapis.XTPayBankApiCall
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourcePayBank(private val callApi: XTPayBankApiCall) {
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
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?> {
        return callApi.payBank(
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
            regid
        )
    }
}