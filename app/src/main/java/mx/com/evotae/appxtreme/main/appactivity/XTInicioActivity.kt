package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityXtinicioBinding

class XTInicioActivity : AppCompatActivity() {
    lateinit var binding: ActivityXtinicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Toolbar config
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        binding = ActivityXtinicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun setupBottomNavMenu(navController: NavController) {
        // TODO STEP 9.3 - Use NavigationUI to set up Bottom Nav
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setupWithNavController(navController)

    }

}