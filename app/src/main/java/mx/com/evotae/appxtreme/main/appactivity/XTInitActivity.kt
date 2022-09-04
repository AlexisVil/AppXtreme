package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityXtinicioBinding

class XTInitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityXtinicioBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Toolbar config
   //     val toolbar = binding.toolbar
     //   setSupportActionBar(toolbar)
        //supportActionBar?.hide()
        binding = ActivityXtinicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupBottomNavMenu(navController)
        setSupportActionBar(binding.toolbar)
        //binding.toolbar.setTitleTextColor(0x00000000)
        setupActionBarWithNavController(navController)

    }

    //Conecta la vista de la bottom bar
    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }

}