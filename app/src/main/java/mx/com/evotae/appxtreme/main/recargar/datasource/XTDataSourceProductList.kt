package mx.com.evotae.appxtreme.main.recargar.datasource

import servicecordinator.callapis.XTProductListApiCall
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceProductList(private val callApi: XTProductListApiCall) {
    suspend fun getProductList(idOperacion: String, marca: String, firma: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseProductList>>?>{
        return callApi.getProductList(idOperacion, marca, firma)

    }
}