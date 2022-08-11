package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTLoginApi {
    @POST(Routers.credentials)
    fun postLogin(): Deferred<Response<XTResponseGeneral<XTResponseLogin>>>
}