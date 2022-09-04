package mx.com.evotae.appxtreme.main.recargar.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_x_t_recarga.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTRecargaBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseProductList

class XTRecargaFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTRecargaBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()
    private val args: XTRecargaFragmentArgs by navArgs()

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
        binding = FragmentXTRecargaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderSpinner()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
//            spinnerProducto.setOnClickListener {
//                viewModelProductList.getProductList(
//                    "listaProductos",
//                    "SERVICIOSTELCEL",
//                    "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
//                )
//            }
            Glide.with(safeActivity).load(args.xtTaeModel.photo).into(binding.ivCarrier)
        }
    }

    private fun initObservers() {
        viewModelProductList.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelProductList.launchError.observe(viewLifecycleOwner, handleError())
        viewModelProductList.getProductList.observe(viewLifecycleOwner, handleProductList())
    }

    private fun handleProductList(): (ArrayList<XTResponseProductList>?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Lista de Productos", Toast.LENGTH_SHORT).show()
    }

    private fun renderSpinner() {
        val productos = arrayListOf<String>(
            "Telcel 10",
            "Telcel 100.0",
            "Telcel 50.00",
            "Telcel 200.00",
            "Telcel 500.00"
        )
        val adapter =
            ArrayAdapter(safeActivity, R.layout.spinner_xtreme_item, productos)
        spinnerProducto.adapter = adapter
    }

}