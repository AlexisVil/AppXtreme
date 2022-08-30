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
    lateinit var productArray : ArrayList<XTResponseData<XTResponseGeneral<XTResponseProductList>>?>
    private val getProductListMLD = SingleLiveEvent<XTResponseProductList>()

    val getProductList: LiveData<XTResponseProductList>
        get() = getProductListMLD

    fun getProductList(idOperacion: String, marca: String, firma: String){
        viewModelScope.launch {
            val resultProdcutList = cuProductList.getProductList(idOperacion, marca, firma)
            productArray = ArrayList()
            if (resultProdcutList.sucess){
                "PRODUCTO -> ${resultProdcutList?.data?.result?.descripcion?.log()}"
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