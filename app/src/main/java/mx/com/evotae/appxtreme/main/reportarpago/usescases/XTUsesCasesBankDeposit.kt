package mx.com.evotae.appxtreme.main.reportarpago.usescases

import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryBankDeposit
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesBankDeposit {
    suspend fun bankDeposit(idOperacion:String) : XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?>
}

class XTUsesCasesBankDepositImpl(private val repository: XTRepositoryBankDeposit): XTUsesCasesBankDeposit{
    override suspend fun bankDeposit(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?> =
        repository.bankDeposit(idOperacion)

}