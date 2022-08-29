package mx.com.evotae.appxtreme.main.recargar.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTRecargaBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseProductList

class XTRecargaFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTRecargaBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if ( context != null ){
            safeActivity = context as Activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXTRecargaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            etProducto.setOnClickListener {
                viewModelProductList.getProductList("listaProductos", "SERVICIOSTELCEL", "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e")
            }
        }
    }

    private fun initObservers() {
        viewModelProductList.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelProductList.launchError.observe(viewLifecycleOwner, handleError())
        viewModelProductList.getProductList.observe(viewLifecycleOwner, handleProductList())
    }

    private fun handleProductList(): (XTResponseProductList?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Lista de Productos", Toast.LENGTH_SHORT).show()
    }
    

}