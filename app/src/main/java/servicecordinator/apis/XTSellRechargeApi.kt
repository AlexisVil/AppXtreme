package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTSellRechargeApi {
    @POST(Routers.ENDPOINT)
    fun sellReacharge(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("claveOperador") claveOperador: String?,
        @Query("regid") regid: String?,
        @Query("versionCode") versionCode: String?,
        @Query("id") id: String?,
        @Query("numeroCelular") numeroCelular: String?
    ): Deferred<Response<XTResponseGeneral<XTResponseSellRecharge>>>
}