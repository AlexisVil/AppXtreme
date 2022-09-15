package mx.com.evotae.appxtreme.main.reportarpago.repository

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceGetBanks
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryGetBanks {
    suspend fun getBanks(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?>
}

class XTRepositoryGetBanksImpl(private val dataSourceGetBanks: XTDataSourceGetBanks):
    XTRepositoryGetBanks {
    override suspend fun getBanks(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?> {
        return dataSourceGetBanks.getBanks(idOperacion)
    }
}