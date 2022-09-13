package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.evotae.appxtreme.databinding.FragmentXTServiceBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.service.adapter.XTServiceAdapter
import mx.com.evotae.appxtreme.main.service.datasource.XTServicesCarrier
import mx.com.evotae.appxtreme.main.service.model.XTServicesModel
import mx.com.evotae.appxtreme.main.service.model.ServicesProvider
import mx.com.evotae.appxtreme.main.service.repository.XTRepositoryServicesProvider


class XTServiceFragment : XTFragmentBase() {
    lateinit var binding : FragmentXTServiceBinding
    private lateinit var safeActivity : Activity
    private lateinit var selectedService: XTServicesModel

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
        binding.recyclerServices.adapter = XTServiceAdapter(XTRepositoryServicesProvider.serviceList) {onItemSelected(it)}
    }

    private fun onItemSelected(serviceModel: XTServicesModel) {
        var idSelected = serviceModel.id.toString()
        selectedService = serviceModel
        openItem()
    }

    fun openItem(){
        val serviceArgs = XTServicesModel(selectedService.name,selectedService.id,selectedService.photo)
        val navigate = XTServiceFragmentDirections.actionXTServiceDestToXTPagarServicioFragment(serviceArgs)
        findNavController().navigate(navigate)
    }

}