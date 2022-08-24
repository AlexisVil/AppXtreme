package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityXtinicioBinding
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.DatePickerFragment

class XTInitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityXtinicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Toolbar config
   //     val toolbar = binding.toolbar
     //   setSupportActionBar(toolbar)
        supportActionBar?.hide()
        binding = ActivityXtinicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupBottomNavMenu(navController)

    }

    //Conecta la vista de la bottom bar
    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavigation?.setupWithNavController(navController)
    }
}