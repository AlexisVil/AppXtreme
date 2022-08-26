package mx.com.evotae.appxtreme.main.tae.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.tae.usescases.XTUsesCasesBrand
import servicecordinator.model.response.XTResponseBrand

class XTViewModelTae(private val cuBrands: XTUsesCasesBrand) : XTViewModelBase() {
    private var getBrandsMLD = SingleLiveEvent<XTResponseBrand>()
    val getBrands: LiveData<XTResponseBrand>
        get() = getBrandsMLD

    fun getBrands(idOperacion: String, firma: String){
        viewModelScope.launch {
            val resultGetBrands = cuBrands.getBrands(idOperacion, firma)
            if(resultGetBrands.sucess){
                "NOMBRE -> ${resultGetBrands.data?.result?.idCarrier?.log()}"
                resultGetBrands.data?.result?.let {
                    getBrandsMLD.postValue(it)
                }
            }else {
                resultGetBrands.exception.let {
                    showError(it?.message.toString())
                }
            }
        }
    }
}