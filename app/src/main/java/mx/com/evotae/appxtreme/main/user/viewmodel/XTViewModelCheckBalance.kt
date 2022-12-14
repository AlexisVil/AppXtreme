package mx.com.evotae.appxtreme.main.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesCheckBalance
import servicecordinator.model.response.XTResponseCheckBalance

class XTViewModelCheckBalance(
    private val cuCheckBalance: XTUsesCasesCheckBalance
) : XTViewModelBase() {
    private var checkBalanceMLD = SingleLiveEvent<ArrayList<XTResponseCheckBalance>>()
    val checkBalance: LiveData<ArrayList<XTResponseCheckBalance>>
        get() = checkBalanceMLD


    fun checkBalance(idOperacion: String, firma: String) {
        viewModelScope.launch {
            val resultCheckBalance = cuCheckBalance.checkBalance(idOperacion, firma)
            if (resultCheckBalance.sucess) {
                resultCheckBalance.data?.result?.forEach {
                    "BOLSA -> ${it.tipoBolsa}".log()
                    "SALDO -> ${it.saldoBolsa}".log()
                }

                resultCheckBalance.data?.result?.let {
                    checkBalanceMLD.postValue(it)
                }
            } else {
                resultCheckBalance.exception.let {
                    showError(it?.message.toString())
                }
            }
        }
    }
}