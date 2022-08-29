package mx.com.evotae.appxtreme.main.reportarpago.repository

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceCheckBalance
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryCheckBalance {
    suspend fun checkBalance(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseCheckBalance>?>
}

class XTRepositoryCheckBalanceImpl(private val dataSourceCheckBalance: XTDataSourceCheckBalance) : XTRepositoryCheckBalance{
    override suspend fun checkBalance(
        idOperacion: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseCheckBalance>?> {
        return dataSourceCheckBalance.checkBalance(idOperacion, firma)
    }

}