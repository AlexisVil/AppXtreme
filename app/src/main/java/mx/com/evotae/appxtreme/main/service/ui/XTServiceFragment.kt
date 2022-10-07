package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.evotae.appxtreme.databinding.FragmentXTServiceBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.service.adapter.XTServiceAdapter
import mx.com.evotae.appxtreme.main.service.model.XTServicesModel
import mx.com.evotae.appxtreme.main.service.repository.XTRepositoryServicesProvider
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.managercall.FIRMA_APP


class XTServiceFragment : XTFragmentBase() {
    lateinit var binding : FragmentXTServiceBinding
    private lateinit var safeActivity : Activity
    private lateinit var selectedService: XTServicesModel
    private val viewModelTae: XTViewModelTae by sharedViewModel() //Encapsula el viewModel
    var dynamicListCarriers = arrayListOf<XTServicesModel>()
    lateinit var elemento : XTServicesModel

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
        //Observers
        initObservers()
        //Listeners
        initListeners()
    }

    private fun initListeners() {
        viewModelTae.getBrands("obtenerMarcas", FIRMA_APP.getPreferenceToString().toString())
    }

    private fun initObservers() {
        viewModelTae.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelTae.launchError.observe(viewLifecycleOwner, handleError())
        viewModelTae.getBrands.observe(viewLifecycleOwner, handlebrand())
    }
    private fun handlebrand(): (ArrayList<XTResponseBrand>?) -> Unit = { objetoArray ->
        dynamicListCarriers.clear()
        objetoArray?.forEach { dataObject ->
            if (dataObject.categoria.equals("SERVICE")){
                var indexLista = XTRepositoryServicesProvider.serviceList.indexOfFirst {
                    it.id == dataObject.idCarrier.toInt()
                }
                elemento = XTRepositoryServicesProvider.serviceList.get(indexLista)
                dynamicListCarriers.add(elemento)
            }
        }
        renderizarLista(dynamicListCarriers)
        println("Filter Carriers : $dynamicListCarriers")
        Toast.makeText(safeActivity, "Obteniendo Catálogo de Servicios", Toast.LENGTH_SHORT).show()
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
    fun renderizarLista(marcas: List<XTServicesModel>){
        binding.recyclerServices.layoutManager = GridLayoutManager(safeActivity, 2)
        binding.recyclerServices.adapter = XTServiceAdapter(marcas) {onItemSelected(it)}
    }

}