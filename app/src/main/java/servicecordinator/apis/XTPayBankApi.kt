package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTPayBankApi {
    @POST(Routers.ENDPOINT)
    fun postPayBank(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("claveOperador") claveOperador: String?,
        @Query("banco") banco: String?,
        @Query("tipoDeposito") tipoDeposito: String?,
        @Query("sucursal") sucursal: String?,
        @Query("referencia") referencia: String?,
        @Query("fecha") fecha: String?,
        @Query("hora") hora: String?,
        @Query("minuto") minuto: String?,
        @Query("monto") monto: String?,
        @Query("recargas") recargas: String?,
        @Query("servicios") servicios: String?,
        @Query("comentarioView") comentarioView: String?,
        @Query("regid") regid: String?
    ): Deferred<Response<XTResponseGeneral<XTResponsePayBank>>>
}