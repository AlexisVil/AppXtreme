package mx.com.evotae.appxtreme.main.splash.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentSplashBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase


class SplashFragment : XTFragmentBase() {
    lateinit var binding: FragmentSplashBinding
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
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Listeners
        initListeners()
    }

    //Listeners

    fun initListeners() {
        val DURATION = 2500

        val handler = Handler()
        handler.postDelayed({
            navigationToLogin()
        }, DURATION.toLong())
    }

    fun navigationToLogin() {
        val navigate = SplashFragmentDirections.actionSplashFragmentToXTLoginFragment()
        findNavController().navigate(navigate)
    }
}