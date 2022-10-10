package mx.com.evotae.appxtreme.main.service.usescases


import mx.com.evotae.appxtreme.main.service.repository.XTRepositoryPayService
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesPayServices {
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

class XTUsesCasesPayServicesImpl(private val repository: XTRepositoryPayService):
    XTUsesCasesPayServices {
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
        return repository.payService(idOperacion, user, pwd, claveOperador, regId, email, id, numeroCuenta, montovar)
    }

}