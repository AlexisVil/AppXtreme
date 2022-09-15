package mx.com.evotae.appxtreme.main.reportarpago.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesBankDeposit
import servicecordinator.model.response.XTResponseBankDeposit

class XTViewModelBankDeposit(
    private val cuBankDeposit: XTUsesCasesBankDeposit
) : XTViewModelBase() {
    private var bankDepositMLD = SingleLiveEvent<ArrayList<XTResponseBankDeposit>>()
    val bankDeposit: LiveData<ArrayList<XTResponseBankDeposit>>
        get() = bankDepositMLD
    fun bankDeposit(idOperacion: String){
        viewModelScope.launch {
            val resultBankDeposit = cuBankDeposit.bankDeposit(idOperacion)
            if (resultBankDeposit.sucess){
                resultBankDeposit.data?.result?.forEach{
                    "TIPO -> ${it.tipo}".log()
                }
                resultBankDeposit.data?.result?.let {
                    bankDepositMLD.postValue(it)
                }
            }else{
                resultBankDeposit.exception?.let {
                    showError(it.message.toString())
                }
            }

        }
    }

}