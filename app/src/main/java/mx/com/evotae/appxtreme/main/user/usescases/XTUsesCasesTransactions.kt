package mx.com.evotae.appxtreme.main.user.usescases

import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLogin
import mx.com.evotae.appxtreme.main.login.usescases.XTUsesCasesLogin
import mx.com.evotae.appxtreme.main.user.repository.XTRepositoryTransactions
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesTransactions {
    suspend fun lastTransactions(idOperacion: String, user: String, pwd: String, claveOperador: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?>
}
class XTUsesCasesTransactionsImpl(private val repository: XTRepositoryTransactions): XTUsesCasesTransactions {
    override suspend fun lastTransactions(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String
    ): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?> =
        repository.lastTransactions(idOperacion, user, pwd, claveOperador)
}