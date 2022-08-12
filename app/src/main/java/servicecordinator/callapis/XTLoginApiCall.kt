package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTLoginApi
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTLoginApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTLoginApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTLoginApi::class.java)
        .builder().instance()



    suspend fun login(idOperacion: String,user: String, pwd: String, regid: String, claveOperador: String): XTResponseData<XTResponseGeneral<XTResponseLogin>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.postLogin(idOperacion, user, pwd, regid, claveOperador).await()
            }
        )
    }
}