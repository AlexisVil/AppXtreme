package mx.com.evotae.appxtreme.main.ventasim.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_x_t_pagar_servicio.*
import kotlinx.android.synthetic.main.fragment_x_t_recarga.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTVentaSimBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
import mx.com.evotae.appxtreme.main.dialogs.ui.TicketDialog
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.tae.datasource.XTDataCarrier
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import servicecordinator.apis.XTSimSellApi
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.model.response.XTResponseSimSell
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP
import servicecordinator.retrofit.model.dataclass.XTRespuestaGenerica
import servicecordinator.router.Routers

class XTVentaSimFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTVentaSimBinding
    private lateinit var safeActivity: Activity
    private val args: XTVentaSimFragmentArgs by navArgs()
    var productos = arrayListOf<String>()
    private var mapOfProducts = mutableMapOf<String, String>()
    lateinit var idCurrentProduct: String
    lateinit var nombreProducto: String
    lateinit var selectedId: String
    private var barecode = ""
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()
    lateinit var nTicket: String
    lateinit var nMonto: String
    lateinit var nDate: String
    lateinit var nAuto: String
    lateinit var numeroSim: String
    lateinit var retrofit: Retrofit
    var currentProduct = ""

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
        binding = FragmentXTVentaSimBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // listeners
        initListeners()
        //observers
        initObservers()
    }

    private fun initListeners() {
        selectedId = args.xtTaeModel.idCarrier.toString()
        nombreProducto = XTDataCarrier.carriersMap[selectedId].toString()
        viewModelProductList.getProductList(
            "listaProductos",
            nombreProducto,
            "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
        )
        binding.apply {
            etRef.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    println("Estas em beforeTextChanged")

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    println("Estas en onTextChanged: $p0")
                    if (!p0.isNullOrBlank()) {
                        ivScan.visibility = View.INVISIBLE
                    } else {
                        ivScan.visibility = View.VISIBLE
                    }

                }

                override fun afterTextChanged(p0: Editable?) {
                    println("Estas en AfterTextChanged")

                }
            })
            ivScan.setOnClickListener {
                Toast.makeText(safeActivity, "Cámara", Toast.LENGTH_SHORT).show()
                initScanner()
            }
            btnRecargaSim.setOnClickListener {

                if (!(etRef.text.toString().length < 4) || etRef.text.isNotEmpty()) {
                    numeroSim = etRef.text.toString()
                    //Instancia de retrofit Sim sell
                    retrofit = Retrofit.Builder()
                        .baseUrl(Routers.HOST)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    ventaSim(
                        "recargasSimCard",
                        USER_APP.getPreferenceToString().toString(),
                        PWD_APP.getPreferenceToString().toString(),
                        OPERATOR_APP.getPreferenceToString().toString(),
                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                        "",
                        idCurrentProduct,
                        numeroSim,
                        ""
                    )
                    etRef.setText("")
                } else {
                    etRef.error = "Ingrese Referencia"
                }
            }
            //Renderiza imágen en el fragment
            Glide.with(safeActivity).load(args.xtTaeModel.photo).into(binding.ivCarrierServices)
        }
    }

    private fun initObservers() {
        //Observers ProductList
        viewModelProductList.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelProductList.launchError.observe(viewLifecycleOwner, handleError())
        viewModelProductList.getProductList.observe(viewLifecycleOwner, handleProductList())
        //Observers SimSell
//        viewModelSimSell.launchLoader.observe(viewLifecycleOwner, handleLoader())
//        viewModelSimSell.launchError.observe(viewLifecycleOwner, handleErrorRecharge())
//        viewModelSimSell.simSell.observe(viewLifecycleOwner, handleSimSell())
    }

    private fun handleSimSell(): (XTResponseSimSell?) -> Unit = { data ->
        nTicket = data?.ticket.toString()
        nMonto = data?.monto.toString()
        nDate = data?.fecha.toString()
        nAuto = data?.autorizacionTelcel.toString()
        Toast.makeText(safeActivity, "SIM RECARGADA", Toast.LENGTH_SHORT).show()
        TicketDialog(nTicket, nMonto, nAuto, numeroSim, nDate).show(
            parentFragmentManager,
            "Dialog"
        )
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
            ArrayAdapter(safeActivity, R.layout.style_spinner, productos)
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
                currentProduct = productos[position].toString()
                idCurrentProduct = mapOfProducts[currentProduct].toString()
                println(idCurrentProduct)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
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

    private fun ventaSim(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        versionCode: String,
        id: String,
        numeroCelular: String,
        montovar: String
    ) {
        val simSellApiCall: XTSimSellApi = retrofit.create(XTSimSellApi::class.java)
        val responseCall: Call<XTRespuestaGenerica<XTResponseSimSell>> =
            simSellApiCall.postSimSell(
                idOperacion,
                user,
                pwd,
                claveOperador,
                regId,
                versionCode,
                id,
                numeroCelular,
                montovar
            )
        val progressDialog = Dialog(safeActivity)
        progressDialog.setContentView(R.layout.custom_progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.show()
        Log.v("URL", responseCall.request().toString())
        responseCall.enqueue(object : Callback<XTRespuestaGenerica<XTResponseSimSell>?> {
            override fun onResponse(
                call: Call<XTRespuestaGenerica<XTResponseSimSell>?>,
                response: Response<XTRespuestaGenerica<XTResponseSimSell>?>
            ) {
                progressDialog.dismiss()
                val data = response.body()?.objeto
                if (response.body()?.redirigir == true) {
                    println("Redirigir = true")
                } else if (response.body()?.operacionExitosa == true) {
                    nTicket = currentProduct
                    nMonto = data?.monto.toString()
                    nDate = data?.fecha.toString()
                    nAuto = data?.autorizacionTelcel.toString()
                    Toast.makeText(safeActivity, "SIM RECARGADA", Toast.LENGTH_SHORT).show()
                    TicketDialog(nTicket, nMonto, nAuto, numeroSim, nDate).show(
                        parentFragmentManager,
                        "Dialog"
                    )
                } else {
                    ErrorDialog(response.body()?.mensaje.toString(),currentProduct).show(
                        parentFragmentManager,
                        "Error"
                    )
                }
            }

            override fun onFailure(
                call: Call<XTRespuestaGenerica<XTResponseSimSell>?>,
                t: Throwable
            ) {
                progressDialog.dismiss()
                ErrorDialog(t.message.toString(), currentProduct).show(
                    parentFragmentManager,
                    "Error"
                )
                Log.e("Error", "onFailure ${t.message}")
            }
        })
    }
}