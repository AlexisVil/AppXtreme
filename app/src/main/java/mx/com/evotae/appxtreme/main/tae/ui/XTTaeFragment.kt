package mx.com.evotae.appxtreme.main.tae.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import mx.com.evotae.appxtreme.databinding.FragmentXTTaeBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.tae.adapter.XTTaeAdapter
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import mx.com.evotae.appxtreme.main.tae.repository.XTTaeBrandsProvider
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBrand
import servicecordinator.retrofit.managercall.FIRMA_APP

class XTTaeFragment : XTFragmentBase() {
    var isSimCard: Boolean = false
    lateinit var binding: FragmentXTTaeBinding
    private lateinit var safeActivity: Activity
    private lateinit var selectedItem: XTTaeModel
    private val viewModelTae: XTViewModelTae by sharedViewModel() //Encapsula el viewModel
    var idSelected: String = ""
    var dynamicListCarriers = arrayListOf<XTTaeModel>()
    lateinit var elemento: XTTaeModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context != null) {
            safeActivity = context as Activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentXTTaeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Observers
        initObservers()
        //Listeners
        initListeners()
    }


    fun onItemSelected(taeModel: XTTaeModel) {
        idSelected = taeModel.idCarrier.toString()
        selectedItem = taeModel
        openItem()
    }

    //Listeners initialization
    fun initListeners() {
        viewModelTae.getBrands("obtenerMarcas", FIRMA_APP.getPreferenceToString().toString())
    }

    //ViewModels
    //observers
    fun initObservers() {
        viewModelTae.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelTae.launchError.observe(viewLifecycleOwner, handleError())
        viewModelTae.getBrands.observe(viewLifecycleOwner, handlebrand())
    }

    private fun handlebrand(): (ArrayList<XTResponseBrand>?) -> Unit = { objetoArray ->
        dynamicListCarriers.clear()
        objetoArray?.forEach { dataObject ->
            if (dataObject.categoria.equals("TOPUP")) {
                var indexLista = XTTaeBrandsProvider.taeList.indexOfFirst {
                    it.idCarrier == dataObject.idCarrier.toInt()
                }
                elemento = XTTaeBrandsProvider.taeList[indexLista]
                //println("elemento: $elemento Indice: $indexLista")
                dynamicListCarriers.add(elemento)

            }
        }
        var sortedList = dynamicListCarriers.sortedWith(compareBy { it.order })
        println("Sorted array $sortedList")
        renderizarLista(sortedList)
//        renderizarLista(dynamicListCarriers)
        println("Filter Carriers : $dynamicListCarriers")
        Toast.makeText(
            safeActivity,
            "Obteniendo catálogo de recarga electrónica",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun openItem() {
        val xtTae = XTTaeModel(
            selectedItem.name,
            selectedItem.idCarrier,
            selectedItem.photo,
            selectedItem.order
        ) //Mandar datos a traves de SafeArgs
        val navigateToSim = XTTaeFragmentDirections.actionXTTaeDestToXTVentaSimFragment(xtTae)
        val navigate = XTTaeFragmentDirections.actionXTTaeDestToXTRecargaFragment(xtTae)
        if (selectedItem.idCarrier.toString().contains("23")) {
            println("Seleccionaste SIMCARD desde TAE y el id es: ${selectedItem.idCarrier}")
            findNavController().navigate(navigateToSim)
        } else {
            println("No seleccionaste SIMCARD, seleccionaste: ${selectedItem.name} & ${selectedItem.idCarrier}")
            findNavController().navigate(navigate)
        }
    }

    fun renderizarLista(marcas: List<XTTaeModel>) {
        binding.recyclerTae.layoutManager = GridLayoutManager(safeActivity, 2)
        binding.recyclerTae.setHasFixedSize(true)
        binding.recyclerTae.adapter = XTTaeAdapter(marcas) { onItemSelected(it) }
    }
}