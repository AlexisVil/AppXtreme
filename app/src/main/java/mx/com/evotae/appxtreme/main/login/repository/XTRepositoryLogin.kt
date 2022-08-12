package mx.com.evotae.appxtreme.main.login.repository

import mx.com.evotae.appxtreme.main.login.datasource.XTDataSourceLogin
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryLogin {
    suspend fun login(idOperacion: String,user: String, pwd: String, regid: String, claveOperador: String): XTResponseData<XTResponseGeneral<XTResponseLogin>?>
}

class XTRepositoryLoginImpl(private val dataSourceLogin: XTDataSourceLogin): XTRepositoryLogin{
    override suspend fun login(idOperacion: String,user: String, pwd: String, regid: String, claveOperador: String): XTResponseData<XTResponseGeneral<XTResponseLogin>?> {
        return dataSourceLogin.login(idOperacion,user, pwd, regid, claveOperador)
    }

}
