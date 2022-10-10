package mx.com.evotae.appxtreme.main.recargar.viewmodel

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.NonDisposableHandle.parent
//import kotlinx.coroutines.launch
//import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
//import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
//import mx.com.evotae.appxtreme.framework.util.extensions.log
//import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
//import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesSellRecharge
//import servicecordinator.model.response.XTResponseSellRecharge
//
//class XTViewModelSellRecharge(
//    private val cuSellRecharge: XTUsesCasesSellRecharge
//) : XTViewModelBase() {
//    private val sellRechargeMLD = SingleLiveEvent<XTResponseSellRecharge>()
//    val sellRecharge: LiveData<XTResponseSellRecharge>
//        get() = sellRechargeMLD
//
//    fun sellRecharge(
//        idOperacion: String,
//        user: String,
//        pwd: String,
//        claveOperador: String,
//        regId: String,
//        versionCode: String,
//        id: String,
//        numeroCelular: String
//    ) {
//        viewModelScope.launch {
//            val resultRecharge = cuSellRecharge.sellRecharge(
//                idOperacion,
//                user,
//                pwd,
//                claveOperador,
//                regId,
//                versionCode,
//                id,
//                numeroCelular
//            )
//            if (resultRecharge.sucess){
//                "TELEFONO -> ${resultRecharge.data?.result?.telefono?.log()}"
//                "TICKET -> ${resultRecharge.data?.result?.ticket?.log()}"
//                resultRecharge.data?.result?.let {
//                    sellRechargeMLD.postValue(it)
//                }
//            }
//             else{
//                resultRecharge.exception.let {
//                    showError(it?.message.toString())
//
//                }
//
//            }
//        }
//    }
//}