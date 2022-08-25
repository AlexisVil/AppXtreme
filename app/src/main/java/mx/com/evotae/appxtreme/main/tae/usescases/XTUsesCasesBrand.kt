package mx.com.evotae.appxtreme.main.tae.usescases

import mx.com.evotae.appxtreme.main.tae.repository.XTRepositoryBrand
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesBrand {
    suspend fun getBrands(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseBrand>?>
}

class XTUsesCasesBrandImpl(private val repository: XTRepositoryBrand): XTUsesCasesBrand {
    override suspend fun getBrands(
        idOperacion: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseBrand>?> = repository.getBrands(idOperacion, firma)
}