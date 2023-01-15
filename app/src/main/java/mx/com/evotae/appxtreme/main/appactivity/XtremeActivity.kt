package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityMainBinding
import kotlin.properties.Delegates
import kotlin.system.exitProcess


class XtremeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    var cuenta = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment

        navHostFragment.childFragmentManager.addOnBackStackChangedListener {
            cuenta = navHostFragment.childFragmentManager.backStackEntryCount
            if (cuenta == 0) {
                Log.i("FragmentStack", "First fragment is open, backstack is empty")
            } else {
                Log.i("FragmentStack", "there are fragments in backstack $cuenta")
            }
        }

    }

    override fun onBackPressed() {
        if (cuenta != 0){
            super.onBackPressed()
            exitProcess(0)
        }else {
            finish()
        }
    }
}