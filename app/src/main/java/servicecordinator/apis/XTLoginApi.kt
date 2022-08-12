package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTLoginApi {
    @POST(Routers.credentials)
    fun postLogin(
        @Query("idOperacion") idOperacion: String?,
        @Query("user") user: String?,
        @Query("pwd") pwd: String?,
        @Query("regid") regid: String?,
        @Query("claveOperador") claveOperador: String?

    ): Deferred<Response<XTResponseGeneral<XTResponseLogin>>>
}
