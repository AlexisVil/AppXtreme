package mx.com.evotae.appxtreme.main.service.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_x_t_pagar_servicio.*
import kotlinx.android.synthetic.main.item_button.*
import mx.com.evotae.appxtreme.databinding.FragmentXTPagarServicioBinding
import mx.com.evotae.appxtreme.databinding.FragmentXTServiceBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.recargar.ui.XTRecargaFragmentArgs

class XTPagarServicioFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTPagarServicioBinding
    private lateinit var safeActivity: Activity
    private val args: XTPagarServicioFragmentArgs by navArgs()

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
        // Inflate the layout for this fragment
        binding = FragmentXTPagarServicioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initObservers()
        initListeners()
    }

    private fun initListeners() {

        binding.apply {
            ivScan.setOnClickListener {
                Toast.makeText(safeActivity, "Camara", Toast.LENGTH_SHORT).show()
                initScanner()
            }
            Glide.with(safeActivity).load(args.services.photo).into(binding.ivCarrierServices)
        }

    }

    private fun initObservers() {
        TODO("Not yet implemented")
    }


    private fun initScanner() {
        val integrator = IntentIntegrator(safeActivity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(safeActivity, "Cancelado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(safeActivity, "EL codigo es: ${result.contents}", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}