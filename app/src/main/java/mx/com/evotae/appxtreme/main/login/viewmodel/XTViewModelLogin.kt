package mx.com.evotae.appxtreme.main.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.login.usescases.XTUsesCasesLogin
import servicecordinator.model.response.XTResponseLogin

class XTViewModelLogin(
    private val cuLogin: XTUsesCasesLogin
) : XTViewModelBase() {
    private var loginMLD = SingleLiveEvent<XTResponseLogin>()
    val login: LiveData<XTResponseLogin>
        get() = loginMLD

    fun login(){
        viewModelScope.launch {
            val resultLogin = cuLogin.login()
            if (resultLogin.sucess){
                "NOMBRE -> ${resultLogin.data?.result?.nombreOp?.log()}"

                resultLogin.data?.result?.let {
                    loginMLD.postValue(it)
                }
            }
            else{
                resultLogin.exception.let {
                    showError(it?.message.toString())
                }
            }
        }
    }

}