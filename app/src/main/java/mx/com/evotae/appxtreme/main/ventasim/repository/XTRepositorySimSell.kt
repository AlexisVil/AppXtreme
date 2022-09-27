package mx.com.evotae.appxtreme.main.ventasim.repository

import mx.com.evotae.appxtreme.main.ventasim.datasource.XTDataSourceSimSell
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositorySimSell {
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

class XTRepositorySimSellImpl(private val dataSourceSimSell: XTDataSourceSimSell): XTRepositorySimSell{
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
    ): XTResponseData<XTResponseGeneral<XTResponseSimSell>?> {
        return dataSourceSimSell.simSell(idOperacion, user, pwd, claveOperador, regid, versionCode, id, numeroCelular, montovar)
    }
}