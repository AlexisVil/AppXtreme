package mx.com.evotae.appxtreme.main.reportarpago.repository

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceBankDeposit
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryBankDeposit {
    suspend fun bankDeposit(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?>
}

class XTRepositoryBankDepositImpl(private val datasourceBankDeposit: XTDataSourceBankDeposit): XTRepositoryBankDeposit{
    override suspend fun bankDeposit(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?> {
        return datasourceBankDeposit.bankDeposit(idOperacion)
    }
}