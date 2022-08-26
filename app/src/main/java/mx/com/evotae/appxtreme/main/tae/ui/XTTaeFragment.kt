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
import mx.com.evotae.appxtreme.databinding.FragmentXTTaeBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.base.XTViewModelBase
import mx.com.evotae.appxtreme.main.tae.adapter.TaeAdapter
import mx.com.evotae.appxtreme.main.tae.model.XTRepositoryTaeP
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBrand

class XTTaeFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTTaeBinding
    private lateinit var safeActivity: Activity

    private val viewModelTae: XTViewModelTae by sharedViewModel() //Encapsula el viewModel

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

    fun onItemSelected(taeModel: XTTaeModel){
        openItem()
        viewModelTae.getBrands("obtenerMarcas", "5f59d36da33080b4a60511d8292029a32c2b248351cded1aa41cd1303e7e4803")
    }

    //Listeners initialization
    fun initListeners(){
        binding.recyclerTae.layoutManager = GridLayoutManager(safeActivity, 2)
        binding.recyclerTae.adapter = TaeAdapter(XTRepositoryTaeP.taeList) {onItemSelected(it)}
    }

    //ViewModels
    //observers
    fun initObservers(){
        viewModelTae.launchLoader.observe(viewLifecycleOwner,handleLoader())
        viewModelTae.launchError.observe(viewLifecycleOwner,handleError())
        viewModelTae.getBrands.observe(viewLifecycleOwner,handlebrand())
    }

    private fun handlebrand(): (XTResponseBrand?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Obteniendo Marcas", Toast.LENGTH_SHORT).show()
    }


    fun openItem(){
        val navigate = XTTaeFragmentDirections.actionXTTaeDestToXTRecargaFragment()
        findNavController().navigate(navigate)
    }
}