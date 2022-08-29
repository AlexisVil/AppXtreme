package mx.com.evotae.appxtreme.main.recargar.repository

import mx.com.evotae.appxtreme.main.recargar.datasource.XTDataSourceProductList
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryProductList {
    suspend fun getProductList(idOperacion: String, marca: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseProductList>?>
}
class XTRepositoryProductListImpl(private val dataSourceProductList: XTDataSourceProductList): XTRepositoryProductList {
    override suspend fun getProductList(
        idOperacion: String,
        marca: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseProductList>?> {
        return dataSourceProductList.getProductList(idOperacion, marca, firma)
    }
}
