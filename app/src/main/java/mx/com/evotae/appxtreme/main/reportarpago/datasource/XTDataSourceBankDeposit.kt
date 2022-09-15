package mx.com.evotae.appxtreme.main.reportarpago.datasource

import servicecordinator.callapis.XTBankDepositApiCall
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTDataSourceBankDeposit(private val callApi: XTBankDepositApiCall) {
    suspend fun bankDeposit(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>?>{
        return callApi.bankDeposit(idOperacion)
    }
}