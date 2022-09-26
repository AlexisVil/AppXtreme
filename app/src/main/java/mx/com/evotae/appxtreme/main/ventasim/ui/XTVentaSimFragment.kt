package mx.com.evotae.appxtreme.main.ventasim.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_x_t_pagar_servicio.*
import kotlinx.android.synthetic.main.fragment_x_t_recarga.*
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTVentaSimBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase

class XTVentaSimFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTVentaSimBinding
    private lateinit var safeActivity: Activity
    var productos = arrayListOf<String>()
    private var mapOfProducts = mutableMapOf<String, String>()
    lateinit var idCurrentProduct: String
    private var barecode = ""

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
        binding = FragmentXTVentaSimBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // listeners
        //observers
    }

    private fun initListeners() {

    }

    private fun initObservers() {

    }

    private fun renderSpinner() {
        val adapter =
            ArrayAdapter(safeActivity, android.R.layout.simple_spinner_item, productos)
        spinnerProducto.adapter = adapter
        println("SPINNER -> $productos")
        spinnerProducto.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val currentProduct = productos[position].toString()
                idCurrentProduct = mapOfProducts[currentProduct].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initScanner() {
        IntentIntegrator.forSupportFragment(this)
            .setTorchEnabled(true)
            .setBarcodeImageEnabled(true)
            .setTorchEnabled(false)
            .setPrompt("Enfoque el código de barras a escanear")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(safeActivity, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                //
                Toast.makeText(safeActivity, "Resultado es: ${result.contents}", Toast.LENGTH_SHORT)
                    .show()
                barecode = result.contents.toString()
                etRef.setText(barecode)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun customProgressDialog() {
        val customProgressDialog = Dialog(safeActivity)
        customProgressDialog.setContentView(R.layout.custom_progress_dialog)
        val handler = Handler()
        val DURATION = 1500
        handler.postDelayed(
            { customProgressDialog.show() },
            DURATION.toLong()
        )
        customProgressDialog.dismiss()
    }
}