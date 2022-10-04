package mx.com.evotae.appxtreme.main.login.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_x_t_login.*
import mx.com.evotae.appxtreme.databinding.FragmentXTLoginBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.framework.util.extensions.savePreferencesToString
import mx.com.evotae.appxtreme.main.appactivity.XTInitActivity
import mx.com.evotae.appxtreme.main.login.viewmodel.XTViewModelLogin
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseLogin
import servicecordinator.retrofit.managercall.*


class XTLoginFragment : XTFragmentBase() {


    lateinit var binding: FragmentXTLoginBinding
    private lateinit var safeActivity: Activity

    private val viewModelLogin: XTViewModelLogin by sharedViewModel()

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
        binding = FragmentXTLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Observadores
        initObservers()
        //Listeners
        initListeners()
    }

    //Declaracion Listeners
    fun initListeners() {
        binding.apply {
            signin.setOnClickListener {

                if (userTxt.text.toString().isEmpty()) {
                    userTxt.requestFocus()
                    userTxt.error = "Ingrese Usuario"
                } else if (passTxt.text.toString().isEmpty()) {
                    passTxt.requestFocus()
                    passTxt.error = "Contraseña incorrecta"
                } else if (operatorTxt.text.toString().isEmpty()) {
                    operatorTxt.requestFocus()
                    operatorTxt.error = "Ingrese Clave de operador"
                } else {
                    viewModelLogin.login(
                        "login",
                        userTxt.text.toString(),
                        passTxt.text.toString(),
                        "80f8cf43-0d26-4876-966e-cc90e13e0f0c",
                        operatorTxt.text.toString()
                    )
                }
            }
        }
    }

    fun initObservers() {
        viewModelLogin.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelLogin.launchError.observe(viewLifecycleOwner, handleError())
        viewModelLogin.login.observe(viewLifecycleOwner, handleLogin())
    }

    private fun handleLogin(): (XTResponseLogin?) -> Unit = { data ->
        USER_APP.savePreferencesToString(userTxt.text.toString())
        PWD_APP.savePreferencesToString(passTxt.text.toString())
        OPERATOR_APP.savePreferencesToString(operatorTxt.text.toString())
        IDPDV.savePreferencesToString(data?.idPv.toString())
        NOMBRE_PDV.savePreferencesToString(data?.nombrePv.toString())
        FIRMA_APP.savePreferencesToString(data?.firma.toString())
        navigateToLoginSucces()
        Toast.makeText(
            safeActivity,
            "Bienvenido ${OPERATOR_APP.getPreferenceToString().toString()}",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun navigateToLoginSucces() {
        startActivity(Intent(safeActivity, XTInitActivity::class.java))
    }
}