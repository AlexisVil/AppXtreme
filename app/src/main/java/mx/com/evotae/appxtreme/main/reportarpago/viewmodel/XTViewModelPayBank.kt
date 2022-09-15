package mx.com.evotae.appxtreme.main.reportarpago.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesPayBank
import servicecordinator.model.response.XTResponsePayBank

class XTViewModelPayBank(
    private val cuPayBank: XTUsesCasesPayBank
) : XTViewModelBase() {
    private var payBankMLD = SingleLiveEvent<XTResponsePayBank>()
    val payBank: LiveData<XTResponsePayBank>
        get() = payBankMLD

    fun payBank(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        banco: String,
        tipoDeposito: String,
        sucursal: String,
        referencia: String,
        fecha: String,
        hora: String,
        minuto: String,
        monto: String,
        recargas: String,
        servicios: String,
        regid: String
    ) {
        viewModelScope.launch {
            val resultPayBank = cuPayBank.payBank(
                idOperacion,
                user,
                pwd,
                claveOperador,
                banco,
                tipoDeposito,
                sucursal,
                referencia,
                fecha,
                hora,
                minuto,
                monto,
                recargas,
                servicios,
                regid
            )
            if (resultPayBank.sucess) {
                "MENSAJE BANCO -> ${resultPayBank.data?.result?.mensaje?.log()}"
                resultPayBank.data?.result?.let {
                    payBankMLD.postValue(it)
                }
            } else {
                resultPayBank.exception?.let {
                    showError(it.message.toString())
                }
            }
        }

    }
}