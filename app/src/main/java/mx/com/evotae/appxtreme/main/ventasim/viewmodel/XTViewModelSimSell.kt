package mx.com.evotae.appxtreme.main.ventasim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.ventasim.usescases.XTUsesCasesSimSell
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTViewModelSimSell(
    private val cuSimSell: XTUsesCasesSimSell
) :XTViewModelBase() {
    private val simSellMLD = SingleLiveEvent<XTResponseSimSell>()
    val simSell : LiveData<XTResponseSimSell>
    get() = simSellMLD

    fun simSell(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regid: String,
        versionCode: String,
        id: String,
        numeroCelular: String,
        montovar: String
    ){
        viewModelScope.launch {
            val resultSimSell = cuSimSell.simSell(idOperacion, user, pwd, claveOperador, regid, versionCode, id, numeroCelular, montovar)
            if (resultSimSell.sucess){
                "AUTORIZACION -> ${resultSimSell.data?.result?.autorizacionTelcel?.log()}"
                resultSimSell.data?.result?.let {
                    simSellMLD.postValue(it)
                }
            }else{
                resultSimSell.exception?.let {
                    showError(it.message.toString())
                }
            }
        }
    }
}