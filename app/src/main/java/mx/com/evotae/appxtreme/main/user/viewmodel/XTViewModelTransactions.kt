package mx.com.evotae.appxtreme.main.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesTransactions
import servicecordinator.model.response.XTResponseTransactions

class XTViewModelTransactions(
    private val cuTransactions: XTUsesCasesTransactions
) : XTViewModelBase() {
    private var transactionsMLD = SingleLiveEvent<ArrayList<XTResponseTransactions>>()
    val transactions: LiveData<ArrayList<XTResponseTransactions>>
        get() = transactionsMLD

    fun transactions(idOperacion: String, user: String, pwd: String, claveOperador: String){
        viewModelScope.launch {
            val resultTransactions = cuTransactions.lastTransactions(idOperacion, user, pwd, claveOperador)
            if (resultTransactions.sucess){
                resultTransactions.data?.result?.forEach{
                    "FECHA -> ${it.fecha}".log()
                    "DESCRIPCION -> ${it.descripcion}".log()
                }


                resultTransactions.data?.result?.let {
                    transactionsMLD.postValue(it)
                }
            }
            else{
                resultTransactions.exception.let {
                    showError(it?.message.toString())
                }
            }
        }
    }

}