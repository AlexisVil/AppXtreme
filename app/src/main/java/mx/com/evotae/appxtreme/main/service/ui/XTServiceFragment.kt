package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTServiceBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.service.adapter.ServiceAdapter
import mx.com.evotae.appxtreme.main.service.datasource.XTServicesCarrier
import mx.com.evotae.appxtreme.main.service.model.Services
import mx.com.evotae.appxtreme.main.service.model.ServicesProvider
import mx.com.evotae.appxtreme.main.tae.datasource.XTDataCarrier
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import mx.com.evotae.appxtreme.main.tae.ui.XTTaeFragmentDirections


class XTServiceFragment : XTFragmentBase() {
    lateinit var binding : FragmentXTServiceBinding
    private lateinit var safeActivity : Activity
    private lateinit var selectedService: Services

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context != null){
            safeActivity = context as Activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXTServiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerServices.layoutManager = GridLayoutManager(safeActivity, 2)
        binding.recyclerServices.adapter = ServiceAdapter(ServicesProvider.servicesList) {onItemSelected(it)}
    }

    private fun onItemSelected(servicesModel: Services) {
        var idSelected = servicesModel.id.toString()
        selectedService = servicesModel
        selectedService.name = XTServicesCarrier.carriersMap[idSelected].toString()
        openItem()
    }

    fun openItem(){
        val serviceArgs = Services(selectedService.name,selectedService.id,selectedService.photo)
        val navigate = XTServiceFragmentDirections.actionXTServiceDestToXTPagarServicioFragment(serviceArgs)
        findNavController().navigate(navigate)
    }

}