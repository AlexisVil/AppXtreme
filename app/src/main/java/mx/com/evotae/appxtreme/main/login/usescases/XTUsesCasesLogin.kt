package mx.com.evotae.appxtreme.main.login.usescases

import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLogin
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesLogin {

    suspend fun login(idOperacion: String,user: String, pwd: String, regid: String, claveOperador: String): XTResponseData<XTResponseGeneral<XTResponseLogin>?>
}

class XTUsesCasesLoginImpl(private val repository: XTRepositoryLogin): XTUsesCasesLogin{
    override suspend fun login(idOperacion: String,user: String, pwd: String, regid: String, claveOperador: String): XTResponseData<XTResponseGeneral<XTResponseLogin>?> =
        repository.login(idOperacion,user, pwd, regid, claveOperador)


}