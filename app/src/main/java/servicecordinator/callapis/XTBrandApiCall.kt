package servicecordinator.callapis

import android.content.Context
import servicecordinator.apis.XTBrandApi
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.builder.XTRetrofitApp
import servicecordinator.retrofit.managercall.XTManagerCall
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

class XTBrandApiCall(private val context: Context): XTManagerCall() {
    private val callApi = XTRetrofitApp
        .Build<XTBrandApi>()
        .setContext(context)
        .setHost(Routers.HOST)
        .setClass(XTBrandApi::class.java)
        .builder().instance()

    suspend fun getBrands(idOperacion: String, firma: String): XTResponseData<XTResponseGeneral<XTResponseBrand>?> {
        return managerCallApi(
            context = context,
            call={
                callApi.getBrand(idOperacion, firma).await()
            }
        )
    }

}