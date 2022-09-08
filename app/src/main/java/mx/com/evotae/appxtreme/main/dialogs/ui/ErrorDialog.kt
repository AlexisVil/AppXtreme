package mx.com.evotae.appxtreme.main.dialogs.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import mx.com.evotae.appxtreme.databinding.DialogErrorRecargaBinding

class ErrorDialog(private val message: String): DialogFragment() {
    private lateinit var binding: DialogErrorRecargaBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding= DialogErrorRecargaBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.apply {
            tvMessage.text = message
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