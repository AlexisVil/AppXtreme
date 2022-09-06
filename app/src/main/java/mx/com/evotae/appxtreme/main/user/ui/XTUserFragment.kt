package mx.com.evotae.appxtreme.main.user.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_reportar_pago.*
import kotlinx.android.synthetic.main.item_button.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTUserBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.framework.util.extensions.log
import mx.com.evotae.appxtreme.framework.util.extensions.wipe
import mx.com.evotae.appxtreme.main.appactivity.XtremeActivity
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelCheckBalance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseCheckBalance
import servicecordinator.retrofit.managercall.OPERATOR_APP

class XTUserFragment : XTFragmentBase() {

    lateinit var binding: FragmentXTUserBinding
    private lateinit var safeActivity: Activity
    private val viewModelCheckBalance: XTViewModelCheckBalance by sharedViewModel()

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
        binding.apply {
            tvOperator.text = OPERATOR_APP.getPreferenceToString().toString()
            btnLogout.setOnClickListener {
                Toast.makeText(safeActivity, "Cerrando sesion", Toast.LENGTH_SHORT).show()
                wipe()
                startActivity(Intent(safeActivity, XtremeActivity::class.java))
            }
            btnPago.setOnClickListener {
                val navigate = XTUserFragmentDirections.actionXTUserDestToReportarPagoFragment()
                findNavController().navigate(navigate)
            }
            btnSaldo.setOnClickListener {
                //crearDialogo()
                viewModelCheckBalance.checkBalance(
                    "consultaSaldo",
                    "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
                )
            }

        }
    }

    fun handleCheckBalance(): (ArrayList<XTResponseCheckBalance>?) -> Unit = {
        val saldos = arrayOf(
            it?.get(0)?.tipoBolsa.toString(),
            it?.get(0)?.saldoBolsa.toString(),
            it?.get(1)?.tipoBolsa.toString(),
            it?.get(1)?.saldoBolsa.toString()
        )
        var mensaje = saldos.get(0) + ": " + saldos.get(1) +" \n" + saldos.get(2) + ": " + saldos.get(3)
        crearDialogo(mensaje)
    }

    private fun initObservers() {
        viewModelCheckBalance.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelCheckBalance.launchError.observe(viewLifecycleOwner, handleError())
        viewModelCheckBalance.checkBalance.observe(viewLifecycleOwner, handleCheckBalance())
    }

    fun crearDialogo(saldo: String) {
        val ventanaDialogo: AlertDialog = AlertDialog.Builder(safeActivity)
            .setTitle("Saldo Disponible")
            .setMessage(saldo)
            .setIcon(R.drawable.ic_check_balance)
            .setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(safeActivity, "Saldo Consultado", Toast.LENGTH_SHORT).show()
            }
            .create()
        ventanaDialogo.show()
    }
}