package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTCheckBalanceApi {
    @GET(Routers.ENDPOINT)
    fun checkBalance(
        @Query("idOperacion") idOperacion: String?,
        @Query("firma") firma: String?
    ): Deferred<Response<XTResponseGeneral<XTResponseCheckBalance>>>
}