package mx.com.evotae.appxtreme.main.service.repository

import mx.com.evotae.appxtreme.main.recargar.datasource.XTDataSourceSellRecharge
import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositorySellRecharge
import mx.com.evotae.appxtreme.main.service.datasource.XTDataSourcePayService
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryPayService {
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
    ): XTResponseData<XTResponseGeneral<XTResponsePayService>?>
}

class XTRepositoryPayServiceImpl(private val dataSourcePayService: XTDataSourcePayService) :
    XTRepositoryPayService {
    override suspend fun payService(
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
        return dataSourcePayService.payService(
            idOperacion, user, pwd, claveOperador, regId, email, id, numeroCuenta, montovar
        )
    }
}