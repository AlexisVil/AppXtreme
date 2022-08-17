package mx.com.evotae.appxtreme.main.splash.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.com.evotae.appxtreme.databinding.FragmentSplashBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.appactivity.XTInitActivity
import servicecordinator.retrofit.managercall.OPERATOR_APP
import servicecordinator.retrofit.managercall.PWD_APP
import servicecordinator.retrofit.managercall.USER_APP


class SplashFragment : XTFragmentBase() {
    lateinit var binding: FragmentSplashBinding
    private lateinit var safeActivity: Activity
    var user_app = ""
    var pwd_app = ""
    var operator_app = ""

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
        user_app = USER_APP.getPreferenceToString().toString()
        pwd_app = PWD_APP.getPreferenceToString().toString()
        operator_app = OPERATOR_APP.getPreferenceToString().toString()
        //Listeners
        initListeners()
    }

    //Listeners
    fun initListeners() {
        val DURATION = 2500
        user_app.toString()

        val handler = Handler()
        handler.postDelayed({
            if (user_app.isEmpty() && pwd_app.isEmpty()) {
                navigationToLogin()
            } else {
                Toast.makeText(safeActivity, "Ya hizo login", Toast.LENGTH_SHORT).show()
                //intent ir a otro activity
                //bottom nav en ese activity
                //nav fragment
                //Main xtreme con grafo y el bottom navigation
                startActivity(Intent(safeActivity,XTInitActivity::class.java))

            }
        }, DURATION.toLong())
    }

    fun navigationToLogin() {
        val navigate = SplashFragmentDirections.actionSplashFragmentToXTLoginFragment()
        findNavController().navigate(navigate)
    }
}