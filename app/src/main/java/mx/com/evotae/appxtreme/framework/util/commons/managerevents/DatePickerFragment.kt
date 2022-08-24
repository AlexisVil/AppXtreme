package mx.com.evotae.appxtreme.framework.util.commons.managerevents

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val e: Calendar = Calendar.getInstance()
        val day: Int = e.get(Calendar.DAY_OF_MONTH)
        val month: Int = e.get(Calendar.MONTH)
        val year: Int = e.get(Calendar.YEAR)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        picker.datePicker.minDate = e.timeInMillis
        //Date Picker Time zone

        return picker
    }
}