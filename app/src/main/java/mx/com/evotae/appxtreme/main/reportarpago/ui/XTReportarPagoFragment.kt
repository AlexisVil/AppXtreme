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
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelBankDeposit
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelGetBanks
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelPayBank
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelCheckBalance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBankDeposit
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.model.response.XTResponseGetBanks
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class XTReportarPagoFragment : XTFragmentBase() {
    lateinit var binding: FragmentReportarPagoBinding
    private lateinit var safeActivity: Activity
    private val viewModelGetBanks: XTViewModelGetBanks by sharedViewModel()
    private val viewModelBankDeposit: XTViewModelBankDeposit by sharedViewModel()
    private val viewModelPayBank: XTViewModelPayBank by sharedViewModel()
    private var mapOfBanks = mutableMapOf<String, String>()
    private var bancos = arrayListOf<String>()
    private var tipoDeposito = arrayListOf<String>()
    lateinit var idCurrentBank: String
    private var bancoOpciones = arrayOf<String>()
    lateinit var nMensajeBanco: String

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

        initObservers()
        initListeners()
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
        viewModelPayBank.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelPayBank.launchError.observe(viewLifecycleOwner, handleError())
        viewModelPayBank.payBank.observe(viewLifecycleOwner, handlePayBank())
    }

    private fun handlePayBank(): (XTResponsePayBank?) -> Unit = { data ->
        nMensajeBanco = data?.objeto.toString()
    }

    private fun handleBankDeposit(): (ArrayList<XTResponseBankDeposit>?) -> Unit = { arrayDeposit ->
        arrayDeposit?.forEach { type ->
            tipoDeposito.add(type.tipo.toString())
        }
        transferenciaDialog()
        println("TIPO DE TRANSFERENCIA -> $tipoDeposito")
    }

    private fun handleGetBanks(): (ArrayList<XTResponseGetBanks>?) -> Unit = {
        it?.forEach { bancoData ->
            mapOfBanks.put(bancoData.id.toString(), bancoData.nombre.toString())
            bancos.add(bancoData.nombre.toString())
        }
        var optionBank: Array<String> = bancos.toTypedArray()
        println("BANCOS $bancos")
        println("OptionBank $optionBank")
        bancosDialog(optionBank)
    }

    private fun initListeners() {
        binding.apply {
            etFecha.setOnClickListener {
                createDatePicker()
            }
            etBanco.setOnClickListener {
                viewModelGetBanks.getBanks("obtener_Bancos")
            }
            etDeposito.setOnClickListener {
                viewModelBankDeposit.bankDeposit("TipoDeposito")
            }
            btnReportar.setOnClickListener {
                if ((etBanco.text.toString().isNotEmpty()) && (etDeposito.text.toString()
                        .isNotEmpty())
                    && (etRef.text.toString().isNotEmpty()) && (etFecha.text.toString()
                        .isNotEmpty())
                    && (etMonto.text.toString().isNotEmpty()) && (etRecarga.text.toString()
                        .isNotEmpty())
                    && (etServicios.text.toString().isNotEmpty())
                ) {
                    viewModelPayBank.payBank(
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
                        etMonto.text.toString(),
                        etRecarga.text.toString(),
                        etServicios.text.toString(),
                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c"
                    )
                    Toast.makeText(safeActivity, "Pago exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    etBanco.requestFocus()
                    etDeposito.requestFocus()
                    etRef.requestFocus()
                    etFecha.requestFocus()
                    etMonto.requestFocus()
                    etRecarga.requestFocus()
                    etServicios.requestFocus()
                    Toast.makeText(safeActivity, "Complete los campos", Toast.LENGTH_SHORT).show()
                }

            }
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
//        println("BANCOS: ${bancos}")
//        val bankOptions = bancos.toTypedArray()
        val singleChoiceDialog: AlertDialog = AlertDialog.Builder(safeActivity)
            .setTitle("Selecciona un banco")
            .setSingleChoiceItems(opciones, 0) { _, position ->
                etBanco.setText(opciones[position])
            }
            .setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(safeActivity, "Aceptar", Toast.LENGTH_SHORT).show()
            }
            .create()
        singleChoiceDialog.show()
    }

    fun transferenciaDialog() {
        val transfer = tipoDeposito.toTypedArray()
        val singleChoiceDialog: AlertDialog = AlertDialog.Builder(safeActivity)
            .setTitle("Selecciona un tipo")
            .setSingleChoiceItems(transfer, 0) { _, position ->
                etDeposito.setText(transfer[position])
            }
            .setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(safeActivity, "Aceptar", Toast.LENGTH_SHORT).show()
            }
            .create()
        singleChoiceDialog.show()
    }


}