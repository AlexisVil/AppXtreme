package mx.com.evotae.appxtreme.main.ventasim.usescases

import mx.com.evotae.appxtreme.main.ventasim.repository.XTRepositorySimSell
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesSimSell {
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
    ): XTResponseData<XTResponseGeneral<XTResponseSimSell>?>
}

class XTUsesCasesSimSellImpl(private val repository: XTRepositorySimSell) : XTUsesCasesSimSell {
    override suspend fun simSell(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regid: String,
        versionCode: String,
        id: String,
        numeroCelular: String,
        montovar: String
    ): XTResponseData<XTResponseGeneral<XTResponseSimSell>?> =
        repository.simSell(
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