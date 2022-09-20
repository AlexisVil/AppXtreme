package mx.com.evotae.appxtreme.main.dialogs.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import mx.com.evotae.appxtreme.databinding.DialogReportarPagoBinding

class ReportarDialog(private val message: String ): DialogFragment() {
    private lateinit var binding: DialogReportarPagoBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogReportarPagoBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.apply {
            tvTicket.text = message
            btnAccept.setOnClickListener {
                dismiss()
            }
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }
}