package mx.com.evotae.appxtreme.main.reportarpago.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import kotlinx.android.synthetic.main.fragment_reportar_pago.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentReportarPagoBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import java.text.SimpleDateFormat
import java.util.*

class XTReportarPagoFragment : XTFragmentBase() {
    lateinit var binding: FragmentReportarPagoBinding
    private lateinit var safeActivity: Activity

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



        binding.apply {
            etFecha.setOnClickListener {
                createDatePicker()
            }
        }
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