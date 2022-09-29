package mx.com.evotae.appxtreme.main.tae.repository

import android.widget.Toast
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBrand

class XTTaeBrandsProvider: XTFragmentBase() {
    private val viewModelTae: XTViewModelTae by sharedViewModel()
    private var catalogoMapa = mutableMapOf<String, String>()

    fun initObservers(){
        viewModelTae.launchLoader.observe(viewLifecycleOwner,handleLoader())
        viewModelTae.launchError.observe(viewLifecycleOwner,handleError())
        viewModelTae.getBrands.observe(viewLifecycleOwner,handlebrand())
    }


    private fun handlebrand(): (ArrayList<XTResponseBrand>?) -> Unit = {
        it?.forEach {
           if ( it.categoria.equals("TOPUP") ){
               catalogoMapa.put(it.carrier, it.idCarrier)
           }
        }
    }

}