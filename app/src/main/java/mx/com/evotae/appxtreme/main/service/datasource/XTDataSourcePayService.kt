package mx.com.evotae.appxtreme.main.service.datasource

import servicecordinator.callapis.XTPayServiceApiCall
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourcePayService(private val callApi: XTPayServiceApiCall) {
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
        return callApi.payService(
            idOperacion,
            user,
            pwd,
            claveOperador,
            regId,
            email,
            id,
            numeroCuenta,
            montovar
        )
    }
}