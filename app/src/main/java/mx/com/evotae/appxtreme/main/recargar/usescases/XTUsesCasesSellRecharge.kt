package mx.com.evotae.appxtreme.main.recargar.usescases

//import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositorySellRecharge
//import servicecordinator.model.response.XTResponseSellRecharge
//import servicecordinator.retrofit.model.dataclass.XTResponseData
//import servicecordinator.retrofit.model.dataclass.XTResponseGeneral
//
//interface XTUsesCasesSellRecharge {
//    suspend fun sellRecharge(idOperacion: String,
//                             user: String,
//                             pwd: String,
//                             claveOperador: String,
//                             regId: String,
//                             versionCode: String,
//                             id: String,
//                             numeroCelular: String
//    ): XTResponseData<XTResponseGeneral<XTResponseSellRecharge>?>
//}
//class XTUsesCasesSellRechargeImpl(private val repository: XTRepositorySellRecharge): XTUsesCasesSellRecharge {
//    override suspend fun sellRecharge(
//        idOperacion: String,
//        user: String,
//        pwd: String,
//        claveOperador: String,
//        regId: String,
//        versionCode: String,
//        id: String,
//        numeroCelular: String
//    ): XTResponseData<XTResponseGeneral<XTResponseSellRecharge>?> {
//        return repository.sellRecharge(idOperacion, user, pwd, claveOperador, regId, versionCode, id, numeroCelular)
//    }
//}