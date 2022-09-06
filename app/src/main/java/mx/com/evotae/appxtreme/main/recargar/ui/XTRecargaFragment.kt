package mx.com.evotae.appxtreme.main.recargar.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_x_t_recarga.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTRecargaBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelSellRecharge
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP

class XTRecargaFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTRecargaBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()
    private val viewModelSellRecharge: XTViewModelSellRecharge by sharedViewModel()
    private val args: XTRecargaFragmentArgs by navArgs()
    var productos = arrayListOf<String>()
    var mapOfProducts = mutableMapOf<String, String>()
    lateinit var idCurrentProduct: String
    lateinit var numeroCelular: String

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
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        viewModelProductList.getProductList(
            "listaProductos",
            args.xtTaeModel.name,
            "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
        )
        binding.apply {
            val inputNumber = etNumber.text.toString()
            val confirm = etConfirmar.text.toString()
            btnRecargar.setOnClickListener {
                if (!(inputNumber.length < 10) && !(confirm.length < 10)) {
                    if (confirm.equals(inputNumber)) {
                        numeroCelular = inputNumber
                        Toast.makeText(
                            safeActivity,
                            "PWd: ${PWD_APP.getPreferenceToString().toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModelSellRecharge.sellRecharge(
                            "ventaRecarga",
                            USER_APP.getPreferenceToString().toString(),
                            PWD_APP.getPreferenceToString().toString(),
                            OPERATOR_APP.getPreferenceToString().toString(),
                            "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                            "",
                            idCurrentProduct,
                            "5555555555"
                        )
                    } else {
                        Toast.makeText(safeActivity, "Numeros no coinciden", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    etNumber.requestFocus()
                    Toast.makeText(safeActivity, "Numero debe tener 10 dígitos", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            //Renderiza imágen en el fragment
            Glide.with(safeActivity).load(args.xtTaeModel.photo).into(binding.ivCarrier)
        }
    }

    private fun initObservers() {
        //Observadores para Lista de Productos
        viewModelProductList.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelProductList.launchError.observe(viewLifecycleOwner, handleError())
        viewModelProductList.getProductList.observe(viewLifecycleOwner, handleProductList())
        //Observadores para Venta de Recarga
        viewModelSellRecharge.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelSellRecharge.launchError.observe(viewLifecycleOwner, handleError())
        viewModelSellRecharge.sellRecharge.observe(viewLifecycleOwner, handleSellRecharge())
    }

    private fun handleSellRecharge(): (XTResponseSellRecharge?) -> Unit = { data ->
        Toast.makeText(
            safeActivity,
            "Recarga Exitosa" + " " + "Ticket: ${data?.ticket}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleProductList(): (ArrayList<XTResponseProductList>?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Obteniendo Productos", Toast.LENGTH_SHORT).show()
        data?.forEach {
            mapOfProducts.put(it.descripcion, it.id)
            productos.add(it.descripcion)
        }
        renderSpinner()
    }

    private fun renderSpinner() {
        val adapter =
            ArrayAdapter(safeActivity, android.R.layout.simple_spinner_item, productos)
        spinnerProducto.adapter = adapter
        println("SPINNER -> $productos")
        spinnerProducto.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val currentProduct = productos[position].toString()
                idCurrentProduct = mapOfProducts[currentProduct].toString()
                //Toast.makeText(safeActivity, idCurrentProduct, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}