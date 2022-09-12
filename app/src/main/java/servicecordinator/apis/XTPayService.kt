package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTPayService {
    @POST(Routers.ENDPOINT)
    fun payService(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("claveOperador") claveOperador: String?,
        @Query("regid") regid: String?,
        @Query("email") email: String?,
        @Query("id") id: String?,
        @Query("numeroCuenta") numeroCuenta: String?,
        @Query("montovar") montovar: String?
    ): Deferred<XTResponseData<XTResponseGeneral<XTResponsePayService>>>
}