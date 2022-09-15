package servicecordinator.apis

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
import servicecordinator.router.Routers

interface XTBankDepositApi {
    @GET(Routers.ENDPOINT)
    fun getBankDeposit(
        @Query("idOperacion") idOperacion: String
    ): Deferred<Response<XTResponseGeneral<ArrayList<XTResponseBankDeposit>>>>
}