package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.model.response.XTResponseVentaRecarga
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.retrofit.model.dataclass.XTRespuestaGenerica
import servicecordinator.router.Routers
import java.lang.reflect.GenericDeclaration

interface XTSellRechargeApi {
    @POST(Routers.ENDPOINT)
    fun postSellRecharge(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("claveOperador") claveOperador: String?,
        @Query("regid") regid: String?,
        @Query("versionCode") versionCode: String?,
        @Query("id") id: String?,
        @Query("numeroCelular") numeroCelular: String?
    ): Call<XTRespuestaGenerica<XTResponseSellRecharge>>
}