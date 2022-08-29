package mx.com.evotae.appxtreme.main.reportarpago.usescases

import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryCheckBalance
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesCheckBalance {
    suspend fun checkBalance(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseCheckBalance>?>
}
class XTUsesCasesCheckBalanceImpl(private val repository: XTRepositoryCheckBalance): XTUsesCasesCheckBalance {
    override suspend fun checkBalance(
        idOperacion: String,
        firma: String
    ): XTResponseData<XTResponseGeneral<XTResponseCheckBalance>?> {
        return repository.checkBalance(idOperacion, firma)
    }

}