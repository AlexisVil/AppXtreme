package mx.com.evotae.appxtreme.main.recargar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesProductList
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

class XTViewModelProductList(
    private val cuProductList: XTUsesCasesProductList
) : XTViewModelBase() {
    private val getProductListMLD = SingleLiveEvent<ArrayList<XTResponseProductList>>()
    val getProductList: LiveData<ArrayList<XTResponseProductList>>
        get() = getProductListMLD

    fun getProductList(idOperacion: String, marca: String, firma: String){
        viewModelScope.launch {
            val resultProdcutList = cuProductList.getProductList(idOperacion, marca, firma)
            if (resultProdcutList.sucess){
                resultProdcutList?.data?.result?.forEach {
                    "CARRIER -> ${it.carrier}".log()
                    "DESCRIPCION DE PRODUCTO -> ${it.descripcion}".log()
                    "MONTO -> ${it.monto.toString()}".log()
                }
                resultProdcutList.data?.result?.let {
                    getProductListMLD.postValue(it)
                }
            } else{
                resultProdcutList.exception.let {
                    showError(it?.message.toString())
                }
            }
        }
    }
}