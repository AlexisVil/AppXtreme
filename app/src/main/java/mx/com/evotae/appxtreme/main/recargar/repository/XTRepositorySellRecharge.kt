package mx.com.evotae.appxtreme.main.recargar.repository

import mx.com.evotae.appxtreme.main.recargar.datasource.XTDataSourceSellRecharge
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositorySellRecharge {
    suspend fun sellRecharge(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        versionCode: String,
        id: String,
        numeroCelular: String
    ): XTResponseData<XTResponseGeneral<XTResponseSellRecharge>?>
}

class XTRepositorySellRechargeImpl(private val dataSourceSellRecharge: XTDataSourceSellRecharge) :
    XTRepositorySellRecharge {
    override suspend fun sellRecharge(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        versionCode: String,
        id: String,
        numeroCelular: String
    ): XTResponseData<XTResponseGeneral<XTResponseSellRecharge>?> {
        return dataSourceSellRecharge.sellRecharge(
            idOperacion,
            user,
            pwd,
            claveOperador,
            regId,
            versionCode,
            id,
            numeroCelular
        )
    }
}