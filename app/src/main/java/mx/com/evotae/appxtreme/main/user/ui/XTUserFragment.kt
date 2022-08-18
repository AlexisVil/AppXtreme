package mx.com.evotae.appxtreme.main.user.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTUserBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase

class XTUserFragment : XTFragmentBase() {

    lateinit var binding : FragmentXTUserBinding
    private lateinit var safeActivity : Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context != null){
            safeActivity = context as Activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXTUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}