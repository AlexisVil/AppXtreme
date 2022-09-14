package mx.com.evotae.appxtreme.main.dialogs.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import mx.com.evotae.appxtreme.databinding.DialogTicketFragmentBinding

class TicketDialog(
    private val ticket: String,
    private val monto: String,
    private val numero: String,
    private val fecha: String
): DialogFragment() {

    private lateinit var binding: DialogTicketFragmentBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogTicketFragmentBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.apply {
            tvTicket.text = ticket
            tvMonto.text = "$ ${monto}"
            tvChargedNumber.text = numero
            tvDate.text = fecha
            btnAccept.setOnClickListener {
                dismiss()
            }
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER_HORIZONTAL)
        return dialog
    }


}