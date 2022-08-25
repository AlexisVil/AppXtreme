package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTBrandApi {
    @GET(Routers.getBrands)
    fun getBrand(
        @Query("idOperacion") idOperacion: String?,
        @Query("firma") firma: String?
    ) :Deferred<Response<XTResponseGeneral<XTResponseBrand>>>
}