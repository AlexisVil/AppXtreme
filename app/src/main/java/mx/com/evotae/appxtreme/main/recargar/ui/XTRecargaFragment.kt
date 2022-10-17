package mx.com.evotae.appxtreme.main.recargar.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
import mx.com.evotae.appxtreme.main.dialogs.ui.ReportarDialog
import mx.com.evotae.appxtreme.main.dialogs.ui.TicketDialog
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import mx.com.evotae.appxtreme.main.tae.datasource.XTDataCarrier
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import servicecordinator.apis.XTSellRechargeApi
import servicecordinator.model.response.XTResponseProductList
import servicecordinator.model.response.XTResponseSellRecharge
import servicecordinator.model.response.XTResponseVentaRecarga
import servicecordinator.retrofit.managercall.FIRMA_APP
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP
import servicecordinator.retrofit.model.dataclass.XTRespuestaGenerica
import servicecordinator.router.Routers
import java.time.Duration

class XTRecargaFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTRecargaBinding
    private lateinit var safeActivity: Activity
    private val viewModelProductList: XTViewModelProductList by sharedViewModel()

    private val args: XTRecargaFragmentArgs by navArgs()
    var productos = arrayListOf<String>()
    private var mapOfProducts = mutableMapOf<String, String>()
    lateinit var idCurrentProduct: String
    lateinit var numeroCelular: String
    lateinit var nTicket: String
    lateinit var nMonto: String
    lateinit var nDate: String
    lateinit var nAuto: String
    lateinit var nombreProducto: String
    lateinit var selectedId: String
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
        binding = FragmentXTRecargaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        selectedId = args.xtTaeModel.idCarrier.toString()
        nombreProducto = XTDataCarrier.carriersMap[selectedId].toString()
        viewModelProductList.getProductList(
            "listaProductos",
            nombreProducto,
            FIRMA_APP.getPreferenceToString().toString()
        )

        binding.apply {

            btnRecargar.setOnClickListener {
                if (!(etNumber.text.toString().length < 10)) {
                    numeroCelular = etNumber.text.toString()
                    println("Numero a recargar: $numeroCelular")
                    if (etNumber.text.toString() != etConfirmar.text.toString()) {
                        etConfirmar.requestFocus()
                        Toast.makeText(safeActivity, "No coincide número", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        //Instancia Retrofit para PayBank
                        retrofit = Retrofit.Builder()
                            .baseUrl(Routers.HOST)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        customProgressDialog()
                        venderRecgarga(
                            "ventaRecarga",
                            USER_APP.getPreferenceToString().toString(),
                            PWD_APP.getPreferenceToString().toString(),
                            OPERATOR_APP.getPreferenceToString().toString(),
                            "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                            "",
                            idCurrentProduct,
                            numeroCelular
                        )
                        /**
                        viewModelSellRecharge.sellRecharge(
                        "ventaRecarga",
                        USER_APP.getPreferenceToString().toString(),
                        PWD_APP.getPreferenceToString().toString(),
                        OPERATOR_APP.getPreferenceToString().toString(),
                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                        "",
                        idCurrentProduct,
                        numeroCelular
                        )
                         **/
                        etNumber.setText("")
                        etConfirmar.setText("")
                    }
                } else {
                    //Nuimero debe tener 10 digitos
                    Toast.makeText(safeActivity, "Número debe tener 10 dígitos", Toast.LENGTH_SHORT)
                        .show()
                    etNumber.requestFocus()
                    etConfirmar.requestFocus()
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
//        viewModelSellRecharge.launchLoader.observe(viewLifecycleOwner, handleLoader())
//        viewModelSellRecharge.sellRecharge.observe(viewLifecycleOwner, handleSellRecharge())
//        viewModelSellRecharge.launchError.observe(viewLifecycleOwner, handleErrorRecharge())
    }

    private fun handleSellRecharge(): (XTResponseSellRecharge?) -> Unit = { data ->
        nTicket = data?.ticket.toString()
        nMonto = data?.monto.toString()
        nDate = data?.fecha.toString()
        nAuto = data?.autorizacionTelcel.toString()
        Toast.makeText(safeActivity, "Recarga exitosa", Toast.LENGTH_SHORT).show()
        TicketDialog(nTicket, nMonto, nAuto, numeroCelular, nDate).show(
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
                val currentProduct = productos[position].toString()
                idCurrentProduct = mapOfProducts[currentProduct].toString()
                println(idCurrentProduct)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    /**
     * Custom Dialog
     */
    private fun customProgressDialog() {
        val customProgressDialog = Dialog(safeActivity)
        customProgressDialog.setContentView(R.layout.custom_progress_dialog)
        val handler = Handler()
        val DURATION = 1500
        handler.postDelayed(
            { customProgressDialog.show() },
            DURATION.toLong()
        )
        customProgressDialog.dismiss()
    }

    private fun venderRecgarga(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        regId: String,
        versionCode: String,
        id: String,
        numeroCelular: String
    ) {
        val sellRechargeService: XTSellRechargeApi = retrofit.create(XTSellRechargeApi::class.java)
        val responseCall: Call<XTRespuestaGenerica<XTResponseSellRecharge>> =
            sellRechargeService.postSellRecharge(
                idOperacion,
                user,
                pwd,
                claveOperador,
                regId,
                versionCode,
                id,
                numeroCelular
            )
        Log.v("URL", responseCall.request().toString())
        responseCall.enqueue(object : Callback<XTRespuestaGenerica<XTResponseSellRecharge>?> {
            override fun onResponse(
                call: Call<XTRespuestaGenerica<XTResponseSellRecharge>?>,
                response: Response<XTRespuestaGenerica<XTResponseSellRecharge>?>
            ) {
                if (response.body()?.redirigir == true){
                    println("Redirigir = true")
                }else if ( response.body()?.operacionExitosa == true ){
                    println("Operacion exitosa = true")
                    Toast.makeText(safeActivity, "true", Toast.LENGTH_SHORT).show()
                }else {
                    println("Operacion Exitosa = false")
                    Toast.makeText(safeActivity, "false", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: Call<XTRespuestaGenerica<XTResponseSellRecharge>?>,
                t: Throwable
            ) {
                Toast.makeText(safeActivity, "Failure ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Error", "onFailure ${t.message}")
            }
        })
    }
}