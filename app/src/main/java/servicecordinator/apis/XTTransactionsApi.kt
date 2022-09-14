package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTTransactionsApi {
    @GET(Routers.ENDPOINT)
    fun getlastTransactions(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("claveOperador") claveOperador: String?
    ): Deferred<Response<XTResponseGeneral<ArrayList<XTResponseTransactions>>>>
}