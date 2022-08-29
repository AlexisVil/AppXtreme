package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTProductListApi
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTProductListApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTProductListApi>()
        .setHost(Routers.HOST)
        .setContext(context)
        .setClass(XTProductListApi::class.java)
        .builder().instance()

    suspend fun getProductList(idOperacion: String, marca: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseProductList>?> {
        return managerCallApi(
            context = context,
            call = {
                callApi.getProductList(idOperacion, marca, firma).await()
            }
        )
    }
}