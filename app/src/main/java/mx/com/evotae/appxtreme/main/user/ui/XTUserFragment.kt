package mx.com.evotae.appxtreme.main.user.ui

import android.app.Activity
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
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTUserBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.wipe
import mx.com.evotae.appxtreme.main.appactivity.XtremeActivity
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelCheckBalance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseCheckBalance

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
        initListeners()
    }

    fun initListeners() {
        binding.apply {
            btnLogout?.setOnClickListener {
                Toast.makeText(safeActivity, "Cerrando sesion", Toast.LENGTH_SHORT).show()
                wipe()
                startActivity(Intent(safeActivity, XtremeActivity::class.java))
            }
            btnPago?.setOnClickListener {
                val navigate = XTUserFragmentDirections.actionXTUserDestToReportarPagoFragment()
                findNavController().navigate(navigate)
            }
            btnSaldo?.setOnClickListener {
                viewModelCheckBalance.checkBalance(
                    "consultaSaldo",
                    "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e"
                )
            }

        }
    }

    private fun handleCheckBalance(): (XTResponseCheckBalance?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Consultando saldo", Toast.LENGTH_SHORT).show()
    }

    private fun initObservers() {
        viewModelCheckBalance.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelCheckBalance.launchError.observe(viewLifecycleOwner, handleError())
        viewModelCheckBalance.checkBalance.observe(viewLifecycleOwner, handleCheckBalance())
    }

}