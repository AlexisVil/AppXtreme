package mx.com.evotae.appxtreme.main.login.usescases

import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLogin
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesLogin {

    suspend fun login(): XTResponseData<XTResponseGeneral<XTResponseLogin>?>
}

class XTUsesCasesLoginImpl(private val repository: XTRepositoryLogin): XTUsesCasesLogin{
    override suspend fun login(): XTResponseData<XTResponseGeneral<XTResponseLogin>?> =
        repository.login()


}