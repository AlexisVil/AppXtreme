package mx.com.evotae.appxtreme.main.tae.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.FragmentXTTaeBinding
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.framework.util.extensions.preferences
import mx.com.evotae.appxtreme.framework.util.extensions.wipe
import mx.com.evotae.appxtreme.main.appactivity.XtremeActivity
import mx.com.evotae.appxtreme.main.tae.adapter.TaeAdapter
import mx.com.evotae.appxtreme.main.tae.model.TaeProvider

class XTTaeFragment : XTFragmentBase() {
    lateinit var binding: FragmentXTTaeBinding
    private lateinit var safeActivity: Activity
    //viewmodel va a qui
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
        // Inflate the layout for this fragment
        binding = FragmentXTTaeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerTae.layoutManager = GridLayoutManager(safeActivity, 2)
        binding.recyclerTae.adapter = TaeAdapter(TaeProvider.taeList)
    }



}