package mx.com.evotae.appxtreme.main.reportarpago.usescases

import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryGetBanks
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesGetBanks {
    suspend fun getBanks(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?>
}

class XTUsesCasesGetBanksImpl(private val repository: XTRepositoryGetBanks): XTUsesCasesGetBanks{
    override suspend fun getBanks(idOperacion: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?> =
        repository.getBanks(idOperacion)
}