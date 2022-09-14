package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTGetBanksApi
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTGetBanksApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTGetBanksApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTGetBanksApi::class.java)
        .builder().instance()

    suspend fun getBanks(
        idOperacion: String
    ): XTResponseData<XTResponseGeneral<ArrayList<XTResponseGetBanks>>?>
    {
        return managerCallApi(
            context = context,
            call = {
                callApi.getbanks(idOperacion).await()
            }
        )
    }
}