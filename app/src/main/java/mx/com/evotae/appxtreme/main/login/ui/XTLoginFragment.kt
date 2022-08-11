package mx.com.evotae.appxtreme.main.login.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityMainBinding
import mx.com.evotae.appxtreme.databinding.FragmentXTLoginBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.login.viewmodel.XTViewModelLogin
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseLogin


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
                viewModelLogin.login()
            }
        }
    }

    fun initObservers() {
        viewModelLogin.launchLoader.observe(viewLifecycleOwner, handleLoader())
        viewModelLogin.launchError.observe(viewLifecycleOwner, handleError())
        viewModelLogin.login.observe(viewLifecycleOwner, handleLogin())
    }

    private fun handleLogin(): (XTResponseLogin?) -> Unit = { data ->
        navigateToLoginSucces()
    }

    fun navigateToLoginSucces() {
        val navigate = XTLoginFragmentDirections.actionXTLoginFragmentToBlankFragment()
        findNavController().navigate(navigate)
    }
}