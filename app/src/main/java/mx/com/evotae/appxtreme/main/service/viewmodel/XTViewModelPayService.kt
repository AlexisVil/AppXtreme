package mx.com.evotae.appxtreme.main.service.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.service.usescases.XTUsesCasesPayServices
import servicecordinator.model.response.XTResponsePayService

class XTViewModelPayService(
    private val cuPayService: XTUsesCasesPayServices
): XTViewModelBase() {
    private val payServiceMLD = SingleLiveEvent<XTResponsePayService>()
            val payService: LiveData<XTResponsePayService>
            get() = payServiceMLD


    fun payService(idOperacion: String,
                   user: String,
                   pwd: String,
                   claveOperador: String,
                   regId: String,
                   email: String,
                   id: String,
                   numeroCuenta: String,
                   montovar: String){
        viewModelScope.launch {
            val resultPayService = cuPayService.payService(idOperacion, user, pwd, claveOperador, regId, email, id, numeroCuenta, montovar)
            if (resultPayService.sucess){
                "TICKET -> ${resultPayService.data?.result?.ticket?.log()}"
                resultPayService.data?.result?.let {
                    payServiceMLD.postValue(it)
                }
            }else{
                resultPayService.exception.let {
                    showError(it?.message.toString())
                }
            }
        }

    }
}