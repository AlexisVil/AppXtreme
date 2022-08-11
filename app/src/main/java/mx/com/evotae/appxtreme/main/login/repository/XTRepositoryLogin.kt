package mx.com.evotae.appxtreme.main.login.repository

import mx.com.evotae.appxtreme.main.login.datasource.XTDataSourceLogin
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryLogin {
    suspend fun login(): XTResponseData<XTResponseGeneral<XTResponseLogin>?>
}

class XTRepositoryLoginImpl(private val dataSourceLogin: XTDataSourceLogin): XTRepositoryLogin{
    override suspend fun login(): XTResponseData<XTResponseGeneral<XTResponseLogin>?> {
        return dataSourceLogin.login()
    }

}
