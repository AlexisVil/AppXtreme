package mx.com.evotae.appxtreme.main.recargar.usescases

import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositoryProductList
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesProductList {
    suspend fun getProductList(idOperacion: String, marca: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseProductList>?>
}

class XTUsesCasesProductListImpl(private val repository: XTRepositoryProductList): XTUsesCasesProductList {
    override suspend fun getProductList(
        idOperacion: String,
        marca: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseProductList>?> {
        return repository.getProductList(idOperacion, marca, firma)
    }

}