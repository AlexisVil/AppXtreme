package mx.com.evotae.appxtreme.main.user.ui

import android.animation.ValueAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_reportar_pago.*
import kotlinx.android.synthetic.main.item_button.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTUserBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.framework.util.extensions.wipe
import mx.com.evotae.appxtreme.main.appactivity.XtremeActivity
import mx.com.evotae.appxtreme.main.dialogs.ui.VentasRecientes
import mx.com.evotae.appxtreme.main.user.adapter.XTVentasAdapter
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelCheckBalance
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelTransactions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.model.response.XTResponseTransactions
import servicecordinator.retrofit.managercall.*

class XTUserFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTUserBinding
    private lateinit var safeActivity: Activity
    private val viewModelCheckBalance: XTViewModelCheckBalance by sharedViewModel()
    private val viewModelTransactions: XTViewModelTransactions by sharedViewModel()
    var transactionsList = mutableListOf<String>()
    var recarga: Double = 0.0
    var servicio: Double = 0.0

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
        binding = FragmentXTUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    fun initListeners() {
        viewModelCheckBalance.checkBalance(
            "consultaSaldo",
            FIRMA_APP.getPreferenceToString().toString()
        )
        binding.apply {
            tvOperator.text = NOMBRE_PDV.getPreferenceToString().toString()
            tvIdPDV.text = IDPDV.getPreferenceToString().toString()
            println(IDPDV.getPreferenceToString().toString())
            println(NOMBRE_PDV.getPreferenceToString().toString())
            btnLogout.setOnClickListener {
                Toast.makeText(safeActivity, "Cerrando sesion", Toast.LENGTH_SHORT).show()
                wipe()
                startActivity(Intent(safeActivity, XtremeActivity::class.java))
            }
            btnPago.setOnClickListener {
                val navigate = XTUserFragmentDirections.actionXTUserDestToReportarPagoFragment()
                findNavController().navigate(navigate)
            }
            btnVentas.setOnClickListener {
                viewModelTransactions.transactions(
                    "movimientos",
                    USER_APP.getPreferenceToString().toString(),
                    PWD_APP.getPreferenceToString().toString(),
                    OPERATOR_APP.getPreferenceToString().toString()
                )
                //VentasRecientes(transactionsList).show(parentFragmentManager, "Dialog")
            }
        }
    }

    fun handleCheckBalance(): (ArrayList<XTResponseCheckBalance>?) -> Unit = {
        it?.forEach { objeto ->
            when (objeto.tipoBolsa) {
                "Recargas" -> recarga = objeto.saldoBolsa
                "Servicios" -> servicio = objeto.saldoBolsa
            }
        }
        animateText(0F, recarga.toFloat(), binding.tvSaldoRecarga)
        animateText(0F, servicio.toFloat(), binding.tvSaldoServicios)
    }

    fun animateText(initValue: Float, finalValue: Float, tv: TextView) {
        val valueAnimator = ValueAnimator.ofFloat(initValue,finalValue)
        valueAnimator.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            val rounded = String.format("%.2f", animatedValue).toFloat()
            tv.text = "$" + rounded
        }
        valueAnimator.start()
    }

    private fun initObservers() {
        //Observadores CheckBalance Saldo Disponible
        viewModelCheckBalance.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelCheckBalance.launchError.observe(viewLifecycleOwner, handleError())
        viewModelCheckBalance.checkBalance.observe(viewLifecycleOwner, handleCheckBalance())
        //Observadores Ultimos movimientos Last Transactions
        viewModelTransactions.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelTransactions.launchError.observe(viewLifecycleOwner, handleError())
        viewModelTransactions.transactions.observe(viewLifecycleOwner, handleTransactions())
    }

    private fun handleTransactions(): (ArrayList<XTResponseTransactions>?) -> Unit = { dataArray ->
        dataArray?.forEach {
            var element = "${it.fecha}\n ${it.numero} \n ${it.descripcion}"
            println(element)
            transactionsList.add(element)
        }
        println(transactionsList)
        VentasRecientes(transactionsList).show(parentFragmentManager, "Ventas")
//        dataArray?.let { objeto ->
//            transactionsList.add(objeto)
//        }
//        println("LISTA ULTIMOS MOV -> $transactionsList")
    }
}