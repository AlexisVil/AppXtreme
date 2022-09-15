package mx.com.evotae.appxtreme.main.reportarpago.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesGetBanks
import servicecordinator.model.response.XTResponseGetBanks

class XTViewModelGetBanks(
    private val cuGetBanks: XTUsesCasesGetBanks
): XTViewModelBase() {
    private var getBanksMLD = SingleLiveEvent<ArrayList<XTResponseGetBanks>>()
    val getBanks : LiveData<ArrayList<XTResponseGetBanks>>
    get() = getBanksMLD

    fun getBanks(idOperacion: String){
        viewModelScope.launch {
            val resultGetBanks = cuGetBanks.getBanks(idOperacion)
            if (resultGetBanks.sucess){
                resultGetBanks.data?.result?.forEach{
                    "BANCO -> ${it.nombre}".log()
                }
                resultGetBanks.data?.result?.let {
                    getBanksMLD.postValue(it)
                }
            }else{
                resultGetBanks.exception?.let {
                    showError(it.message.toString())
                }
            }
        }
    }
}