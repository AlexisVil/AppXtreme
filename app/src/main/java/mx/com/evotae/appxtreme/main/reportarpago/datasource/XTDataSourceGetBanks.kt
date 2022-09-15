package mx.com.evotae.appxtreme.main.reportarpago.datasource

import servicecordinator.callapis.XTGetBanksApiCall
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceGetBanks(private val callApi: XTGetBanksApiCall) {
    suspend fun getBanks(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?>{
        return callApi.getBanks(idOperacion)
    }
}