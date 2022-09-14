package mx.com.evotae.appxtreme.main.dialogs.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.DialogVentasBinding
import mx.com.evotae.appxtreme.main.user.adapter.XTVentasAdapter

class VentasRecientes(private val transactionList: List<String>): DialogFragment() {
    private lateinit var binding: DialogVentasBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogVentasBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.recyclerVentas.layoutManager= LinearLayoutManager(context)
        binding.recyclerVentas.adapter = XTVentasAdapter(transactionList)
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER_HORIZONTAL)
        return dialog
    }
}