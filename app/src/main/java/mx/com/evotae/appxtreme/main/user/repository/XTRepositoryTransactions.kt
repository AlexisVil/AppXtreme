package mx.com.evotae.appxtreme.main.user.repository

import mx.com.evotae.appxtreme.main.login.datasource.XTDataSourceLogin
import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLogin
import mx.com.evotae.appxtreme.main.user.datasource.XTDataSourceTransactions
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryTransactions {
    suspend fun lastTransactions(idOperacion: String,user: String, pwd: String, claveOperador: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?>
}

class XTRepositoryTransactionsImpl(private val dataSourceTransactions: XTDataSourceTransactions ): XTRepositoryTransactions {
    override suspend fun lastTransactions(idOperacion: String,user: String, pwd: String, claveOperador: String): XTResponseData<XTResponseGeneral<ArrayList<XTResponseTransactions>>?> {
        return dataSourceTransactions.lastTransactions(idOperacion, user, pwd, claveOperador)
    }
}