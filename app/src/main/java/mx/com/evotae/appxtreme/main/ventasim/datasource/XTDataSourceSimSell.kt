package mx.com.evotae.appxtreme.main.ventasim.datasource

import servicecordinator.callapis.XTSimSellApiCall
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceSimSell(private val callApi: XTSimSellApiCall) {
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
        return callApi.simSell(
            idOperacion,
            user,
            pwd,
            claveOperador,
            regid,
            versionCode,
            id,
            numeroCelular,
            montovar
        )
    }
}