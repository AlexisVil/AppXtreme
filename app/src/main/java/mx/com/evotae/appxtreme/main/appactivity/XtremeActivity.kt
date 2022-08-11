package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityMainBinding
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.framework.util.extensions.savePreferencesToString
import servicecordinator.retrofit.managercall.APP_NAME

class XtremeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //APP_NAME.savePreferencesToString("Alexis")
        println(APP_NAME.getPreferenceToString().toString())
    }
}