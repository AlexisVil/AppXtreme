package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_x_t_pagar_servicio.*
import kotlinx.android.synthetic.main.fragment_x_t_user.*
import kotlinx.android.synthetic.main.item_button.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTPagarServicioBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
import mx.com.evotae.appxtreme.main.dialogs.ui.TicketDialog
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.service.datasource.XTServicesCarrier
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import servicecordinator.apis.XTPayServiceApi
import servicecordinator.model.response.XTResponsePayService
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.retrofit.managercall.FIRMA_APP
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP
import servicecordinator.retrofit.model.dataclass.XTRespuestaGenerica
import servicecordinator.router.Routers

class XTPagarServicioFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTPagarServicioBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()
    private val args: XTPagarServicioFragmentArgs by navArgs()
    private var barecode = ""
    private var mapOfProducts = mutableMapOf<String, String>()
    private lateinit var idCurrentProduct: String
    private lateinit var numeroReferencia: String
    private lateinit var nTicket: String
    private lateinit var nMonto: String
    private lateinit var nDate: String
    private lateinit var nAuto: String
    lateinit var nombreProducto: String
    lateinit var selectedId: String
    lateinit var montoIngresado: String
    lateinit var retrofit: Retrofit

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
            FIRMA_APP.getPreferenceToString().toString()
        )

        binding.apply {
            ivScan.setOnClickListener {
                Toast.makeText(safeActivity, "Cámara", Toast.LENGTH_SHORT).show()
                initScanner()
            }
            btnServicio.setOnClickListener {

                println(idCurrentProduct)
                if (!(etRef.text.toString().length < 4)) {
                    numeroReferencia = etRef.text.toString()
                    if (etMontoServicio.text.toString().isEmpty()) {
                        Toast.makeText(safeActivity, "Ingrese un monto", Toast.LENGTH_SHORT).show()
                    } else {
                        //Instancia Retrofit para Pay Service
                        retrofit = Retrofit.Builder()
                            .baseUrl(Routers.HOST)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val customProgressDialog = Dialog(safeActivity)
                        customProgressDialog.setContentView(R.layout.custom_progress_dialog)
                        customProgressDialog.setCancelable(true)
                        customProgressDialog.show()
                        montoIngresado = etMontoServicio.text.toString()
                        pagarServicio(
                            "pagoServicios",
                            USER_APP.getPreferenceToString().toString(),
                            PWD_APP.getPreferenceToString().toString(),
                            OPERATOR_APP.getPreferenceToString().toString(),
                            "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                            "",
                            idCurrentProduct,
                            numeroReferencia,
                            montoIngresado
                        )
                        etRef.setText("")
                        etMontoServicio.setText("")
                        if (customProgressDialog.isShowing)
                            customProgressDialog.dismiss()
                    }
                } else {
                    Toast.makeText(
                        safeActivity,
                        "Referencia incorrecta",
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
        //Observadores para Pago de servicio
//        viewModelPayService.launchLoader.observe(viewLifecycleOwner, handleLoader())
//        viewModelPayService.launchError.observe(viewLifecycleOwner, handleErrorRecharge())
//        viewModelPayService.payService.observe(viewLifecycleOwner, handlerPayService())
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
        nAuto = data?.autorizacionTelcel.toString()
        Toast.makeText(safeActivity, "Pago Realizado", Toast.LENGTH_SHORT).show()
        TicketDialog(nTicket, nMonto, nAuto, numeroReferencia, nDate).show(
            parentFragmentManager,
            "Dialog"
        )
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

    private fun pagarServicio(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        email: String,
        id: String,
        numeroCuenta: String,
        montovar: String
    ) {
        val payServiceApiCall: XTPayServiceApi = retrofit.create(XTPayServiceApi::class.java)
        val responseCall: Call<XTRespuestaGenerica<XTResponsePayService>> =
            payServiceApiCall.postPayService(
                idOperacion,
                user,
                pwd,
                claveOperador,
                regId,
                email,
                id,
                numeroCuenta,
                montovar
            )
        Log.v("URL", responseCall.request().toString())
        responseCall.enqueue(object : Callback<XTRespuestaGenerica<XTResponsePayService>?> {
            override fun onResponse(
                call: Call<XTRespuestaGenerica<XTResponsePayService>?>,
                response: Response<XTRespuestaGenerica<XTResponsePayService>?>
            ) {
                val data = response.body()?.objeto
                if (response.body()?.redirigir == true) {
                    println("Redirigir = true")
                } else if (response.body()?.operacionExitosa == true) {
                    nTicket = data?.ticket.toString()
                    nMonto = data?.monto.toString()
                    nDate = data?.fecha.toString()
                    nAuto = data?.autorizacionTelcel.toString()
                    Toast.makeText(safeActivity, "Pago Realizado", Toast.LENGTH_SHORT).show()
                    TicketDialog(nTicket, nMonto, nAuto, numeroReferencia, nDate).show(
                        parentFragmentManager,
                        "Dialog"
                    )
                } else {
                    ErrorDialog(response.body()?.mensaje.toString()).show(
                        parentFragmentManager,
                        "Error"
                    )
                }
            }

            override fun onFailure(
                call: Call<XTRespuestaGenerica<XTResponsePayService>?>,
                t: Throwable
            ) {
                Toast.makeText(safeActivity, "Failure ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Error", "onFailure ${t.message}")
            }
        })
    }
}

