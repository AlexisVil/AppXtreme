package mx.com.evotae.appxtreme.main.reportarpago.ui

import android.app.Activity
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
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelCheckBalance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseCheckBalance
import java.text.SimpleDateFormat
import java.util.*

class XTReportarPagoFragment : XTFragmentBase() {
    lateinit var binding: FragmentReportarPagoBinding
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
        binding = FragmentReportarPagoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            etFecha.setOnClickListener {
                createDatePicker()
            }
            etRecarga.setOnClickListener {
                viewModelCheckBalance.checkBalance("consultaSaldo", "2cb4fffb7223c1518c0fff47f1011dd2b1f2f26431f445f0db06ec99c56ae72e")
            }
        }
    }

    private fun initObservers() {
        viewModelCheckBalance.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelCheckBalance.launchError.observe(viewLifecycleOwner, handleError())
        viewModelCheckBalance.checkBalance.observe(viewLifecycleOwner, handleCheckBalance())
    }

    private fun handleCheckBalance(): (XTResponseCheckBalance?) -> Unit = { data ->
        Toast.makeText(safeActivity, "Consultando saldo", Toast.LENGTH_SHORT).show()
    }

    private fun createDatePicker(){
        val c = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(c)
        }
        DatePickerDialog(safeActivity,R.style.AppTheme_DatePickerDialog, datePicker, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun updateLabel(c: Calendar) {
        val calenderFormat="dd/MM/yy"
        val sdf = SimpleDateFormat(calenderFormat, Locale.ROOT)
        etFecha.setText(sdf.format(c.timeInMillis))
    }


}