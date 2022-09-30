package mx.com.evotae.appxtreme.main.reportarpago.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_reportar_pago.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentReportarPagoBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
import mx.com.evotae.appxtreme.main.dialogs.ui.ReportarDialog
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelBankDeposit
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelGetBanks
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import servicecordinator.apis.XTPayBankApi
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP
import servicecordinator.router.Routers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class XTReportarPagoFragment : XTFragmentBase() {
    lateinit var binding: FragmentReportarPagoBinding
    private lateinit var safeActivity: Activity
    private val viewModelGetBanks: XTViewModelGetBanks by sharedViewModel()
    private val viewModelBankDeposit: XTViewModelBankDeposit by sharedViewModel()

    // private val viewModelPayBank: XTViewModelPayBank by sharedViewModel()
    private var mapOfBanks = mutableMapOf<String, String>()
    private var bancos = arrayListOf<String>()
    private var tipoDeposito = arrayListOf<String>()
    lateinit var idCurrentBank: String
    private var bancoOpciones = arrayOf<String>()
    lateinit var nMensajeBanco: String
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
        binding = FragmentReportarPagoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init Oberservers
        initObservers()
        //Init Listeners
        initListeners()
    }

    fun initListeners() {
        viewModelGetBanks.getBanks("obtener_Bancos")
        viewModelBankDeposit.bankDeposit("TipoDeposito")
        binding.apply {
            etFecha.setOnClickListener {
                createDatePicker()
            }
            etBanco.setOnClickListener {
                var optionBank: Array<String> = bancos.toTypedArray()
                bancosDialog(optionBank)
            }
            etDeposito.setOnClickListener {
                var optionTransfer: Array<String> = tipoDeposito.toTypedArray()
                transferenciaDialog(optionTransfer)
            }
            btnReportar.setOnClickListener {
                //Instancia Retrofit para PayBank
                retrofit = Retrofit.Builder()
                    .baseUrl(Routers.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
//                viewModelPayBank.payBank(
//                    "pagos_reportarPago",
//                        USER_APP.getPreferenceToString().toString(),
//                        PWD_APP.getPreferenceToString().toString(),
//                        OPERATOR_APP.getPreferenceToString().toString(),
//                        etBanco.text.toString(),
//                        etDeposito.text.toString(),
//                        "",
//                        etRef.text.toString(),
//                        etFecha.text.toString(),
//                        "00",
//                        "59",
//                        "0",
//                        "0",
//                        "0",
//                        "",
//                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c"
//                )
                if (etBanco.text.isEmpty()) {
                    etBanco.requestFocus()
                    etBanco.error = "Favor de completar el campo"
                } else if (etDeposito.text.isEmpty()) {
                    etDeposito.requestFocus()
                    etDeposito.error = "Favor de completar el campo"
                } else if (etRef.text.isEmpty()) {
                    etRef.requestFocus()
                    etRef.error = "Favor de completar el campo"
                } else if (etFecha.text.isEmpty()) {
                    etFecha.requestFocus()
                    etFecha.error = "Favor de completar el campo"
                } else if (etMonto.text.toString().toInt() != etRecarga.text.toString()
                        .toInt() + etServicios.text.toString().toInt()
                ) {
                    etMonto.requestFocus()
                    etMonto.error = "El monto debe coincidar con saldos asignados"
                } else {
                    reportarPago(
                        "pagos_reportarPago",
                        USER_APP.getPreferenceToString().toString(),
                        PWD_APP.getPreferenceToString().toString(),
                        OPERATOR_APP.getPreferenceToString().toString(),
                        etBanco.text.toString(),
                        etDeposito.text.toString(),
                        "",
                        etRef.text.toString(),
                        etFecha.text.toString(),
                        "00",
                        "59",
                        "0",
                        "0",
                        "0",
                        "",
                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c"
                    )
                }
            }
        }
    }

    private fun initObservers() {
        //Observadores GetBanks
        viewModelGetBanks.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelGetBanks.launchError.observe(viewLifecycleOwner, handleError())
        viewModelGetBanks.getBanks.observe(viewLifecycleOwner, handleGetBanks())
        //Observadores Bank Deposit
        viewModelBankDeposit.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelBankDeposit.launchError.observe(viewLifecycleOwner, handleError())
        viewModelBankDeposit.bankDeposit.observe(viewLifecycleOwner, handleBankDeposit())
        //Observadores Pay Bank
//        viewModelPayBank.launchLoader.observe(viewLifecycleOwner, handleLoader())
//        viewModelPayBank.launchError.observe(viewLifecycleOwner, handleError())
//        viewModelPayBank.payBank.observe(viewLifecycleOwner, handlePayBank())
    }

//    private fun handlePayBank(): (XTResponsePayBank?) -> Unit = { data ->
//        nMensajeBanco = data?.objeto.toString()
//        println(nMensajeBanco)
//        Toast.makeText(safeActivity, "Pago exitoso", Toast.LENGTH_SHORT).show()
//    }

    private fun handleBankDeposit(): (ArrayList<XTResponseBankDeposit>?) -> Unit = { arrayDeposit ->
        arrayDeposit?.forEach { type ->
            tipoDeposito.add(type.tipo.toString())
        }
    }

    private fun handleGetBanks(): (ArrayList<XTResponseGetBanks>?) -> Unit = {
        it?.forEach { bancoData ->
            mapOfBanks.put(bancoData.id.toString(), bancoData.nombre.toString())
            bancos.add(bancoData.nombre.toString())
        }
    }

    private fun createDatePicker() {
        val c = Calendar.getInstance()
        val datePicker =
            DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel(c)
            }
        DatePickerDialog(
            safeActivity,
            R.style.AppTheme_DatePickerDialog,
            datePicker,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateLabel(c: Calendar) {
        val calenderFormat = "yyyy/MM/dd"
        val sdf = SimpleDateFormat(calenderFormat, Locale.ROOT)
        etFecha.setText(sdf.format(c.timeInMillis))
    }

    fun bancosDialog(opciones: Array<String>) {
        val singleChoiceDialog: AlertDialog =
            AlertDialog.Builder(safeActivity, R.style.AppTheme_DatePickerDialog)
                .setTitle("Selecciona un banco")
                .setSingleChoiceItems(opciones, 0) { _, position ->
                    etBanco.setText(opciones[position])
                }
                .setPositiveButton("Aceptar") { _, _ ->
                }
                .create()
        singleChoiceDialog.show()
    }

    fun transferenciaDialog(transfer: Array<String>) {

        val singleChoiceDialog: AlertDialog =
            AlertDialog.Builder(safeActivity, R.style.AppTheme_DatePickerDialog)
                .setTitle("Selecciona un tipo")
                .setSingleChoiceItems(transfer, 0) { _, position ->
                    etDeposito.setText(transfer[position])
                }
                .setPositiveButton("Aceptar") { _, _ ->
                }
                .create()
        singleChoiceDialog.show()
    }

    private fun reportarPago(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        banco: String,
        tipoDeposito: String,
        sucursal: String,
        referencia: String,
        fecha: String,
        hora: String,
        minuto: String,
        monto: String,
        recargas: String,
        servicios: String,
        comentarioView: String,
        regid: String
    ) {
        val payBankService: XTPayBankApi = retrofit.create(XTPayBankApi::class.java)
        val responseCall: Call<XTResponsePayBank> = payBankService.postPayBank(
            idOperacion,
            user,
            pwd,
            claveOperador,
            banco,
            tipoDeposito,
            sucursal,
            referencia,
            fecha,
            hora,
            minuto,
            monto,
            recargas,
            servicios,
            comentarioView,
            regid
        )
        responseCall.enqueue(object : Callback<XTResponsePayBank?> {
            override fun onResponse(
                call: Call<XTResponsePayBank?>,
                response: Response<XTResponsePayBank?>
            ) {
                if (response.body()?.redirigir == true) {
                    println("Ha entrado al error")
                } else if (response.body()?.operacionExitosa == true) {
                    ReportarDialog(response.body()?.objeto.toString()).show(
                        parentFragmentManager,
                        "Dialog"
                    )
                    println("Este es el mensaje del objeto exitoso ${response.body()?.objeto.toString()}")
                } else {
                    println("Este es el mensaje de error de la respuesta ${response.body()?.mensaje.toString()}")
                    ErrorDialog(response.body()?.mensaje.toString()).show(
                        parentFragmentManager,
                        "Error"
                    )
                }
            }

            override fun onFailure(call: Call<XTResponsePayBank?>, t: Throwable) {
                println("Error en la conexion onFailure")
                Toast.makeText(
                    safeActivity,
                    "Algo ha salido mal , revise su conexión a internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}