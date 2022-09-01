package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTProductListApi {
    @GET(Routers.ENDPOINT)
    fun getProductList(
        @Query("idOperacion") idOperacion: String?,
        @Query("marca") marca: String?,
        @Query("firma") firma: String?
    ): Deferred<Response<XTResponseGeneral<ArrayList<XTResponseProductList>>>>
}