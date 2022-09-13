package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_x_t_pagar_servicio.*
import kotlinx.android.synthetic.main.fragment_x_t_user.*
import kotlinx.android.synthetic.main.item_button.*
import mx.com.evotae.appxtreme.databinding.FragmentXTPagarServicioBinding
import mx.com.evotae.appxtreme.databinding.FragmentXTServiceBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.dialogs.ui.TicketDialog
import mx.com.evotae.appxtreme.main.recargar.ui.XTRecargaFragmentArgs
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelSellRecharge
import mx.com.evotae.appxtreme.main.service.datasource.XTServicesCarrier
import mx.com.evotae.appxtreme.main.service.viewmodel.XTViewModelPayService
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP

class XTPagarServicioFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTPagarServicioBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()
    private val viewModelPayService: XTViewModelPayService by sharedViewModel()
    private val args: XTPagarServicioFragmentArgs by navArgs()
    private var barecode = ""
    private var mapOfProducts = mutableMapOf<String, String>()
    private lateinit var idCurrentProduct: String
    private lateinit var numeroReferencia: String
    private lateinit var nTicket: String
    private lateinit var nMonto: String
    private lateinit var nDate: String
    lateinit var nombreProducto: String
    lateinit var selectedId: String
    lateinit var montoIngresado: String

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
        binding = FragmentXTPagarServicioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        selectedId = args.xtServiceModel.id.toString()
        nombreProducto = XTServicesCarrier.carriersMap[selectedId].toString()
        viewModelProductList.getProductList(
            "listaProductos",
            nombreProducto,
            "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
        )

        binding.apply {
            ivScan.setOnClickListener {
                Toast.makeText(safeActivity, "Camara", Toast.LENGTH_SHORT).show()
                initScanner()
            }
            btnServicio.setOnClickListener {
                if (!(etRef.text.toString().length < 10)) {
                    numeroReferencia = etRef.text.toString()
                    if (etMontoServicio.text.toString().isEmpty()) {
                        Toast.makeText(safeActivity, "Ingrese un monto", Toast.LENGTH_SHORT).show()
                    } else {
                        montoIngresado = etMontoServicio.text.toString()
                        viewModelPayService.payService(
                            "pagoServicios",
                            USER_APP.getPreferenceToString().toString(),
                            PWD_APP.getPreferenceToString().toString(),
                            OPERATOR_APP.getPreferenceToString().toString(),
                            "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                            "",
                            "102",
                            numeroReferencia,
                            montoIngresado
                        )
                    }
                } else {
                    Toast.makeText(
                        safeActivity,
                        "Referencia debe tener 10 dígitos",
                        Toast.LENGTH_SHORT
                    ).show()
                    etRef.requestFocus()
                }
            }

            Glide.with(safeActivity).load(args.xtServiceModel.photo).into(binding.ivCarrierServices)

        }

    }

    private fun initObservers() {
        //Observadores para Lista de Productos
        viewModelProductList.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelProductList.launchError.observe(viewLifecycleOwner, handleError())
        viewModelProductList.getProductList.observe(viewLifecycleOwner, handleProductList())
        //Observadores para Venta de Recarga
        viewModelPayService.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelPayService.launchError.observe(viewLifecycleOwner, handleError())
        viewModelPayService.payService.observe(viewLifecycleOwner, handlerPayService())
    }

    private fun handleProductList(): (ArrayList<XTResponseProductList>?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Obteniendo Productos", Toast.LENGTH_SHORT).show()
        data?.forEach {
            mapOfProducts.put(it.descripcion, it.id)
            idCurrentProduct = it.id
        }
    }

    private fun handlerPayService(): (XTResponsePayService?) -> Unit = { data ->
        nTicket = data?.ticket.toString()
        nMonto = data?.monto.toString()
        nDate = data?.fecha.toString()
        Toast.makeText(safeActivity, "Pago Realizado", Toast.LENGTH_SHORT).show()
        TicketDialog(nTicket, nMonto, numeroReferencia, nDate).show(parentFragmentManager, "Dialog")
    }


    private fun initScanner() {
        IntentIntegrator.forSupportFragment(this)
            .setTorchEnabled(true)
            .setBarcodeImageEnabled(true)
            .setTorchEnabled(false)
            .setPrompt("Enfoque el código de barras a escanear")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(safeActivity, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                //
                Toast.makeText(safeActivity, "Resultado es: ${result.contents}", Toast.LENGTH_SHORT)
                    .show()
                barecode = result.contents.toString()
                etRef.setText(barecode)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

