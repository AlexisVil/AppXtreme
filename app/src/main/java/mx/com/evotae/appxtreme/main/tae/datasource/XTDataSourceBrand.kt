package mx.com.evotae.appxtreme.main.tae.datasource

import servicecordinator.callapis.XTBrandApiCall
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceBrand(private val callApi: XTBrandApiCall) {
    suspend fun getBrands(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBrand>>?> {
        return callApi.getBrands(idOperacion, firma)
    }
}