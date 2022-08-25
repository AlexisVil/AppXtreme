package mx.com.evotae.appxtreme.main.tae.repository

import mx.com.evotae.appxtreme.main.tae.datasource.XTDataSourceBrand
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryBrand {
    suspend fun getBrands(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseBrand>?>
}

class XTRepositoryBrandImpl(private val dataSourceBrand: XTDataSourceBrand): XTRepositoryBrand {
    override suspend fun getBrands(
        idOperacion: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseBrand>?> {
        return dataSourceBrand.getBrands(idOperacion, firma)
    }

}