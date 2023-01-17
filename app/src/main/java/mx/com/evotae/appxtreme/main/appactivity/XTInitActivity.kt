package mx.com.evotae.appxtreme.main.appactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ActivityXtinicioBinding
import kotlin.system.exitProcess

class XTInitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityXtinicioBinding
    private lateinit var navController: NavController

    private lateinit var navHostFragment: NavHostFragment
    var cuenta = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXtinicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupBottomNavMenu(navController)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)
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
        if (cuenta == 0) {
            super.onBackPressed()
            //exitProcess(0)
            finish()
        } else {
            navHostFragment.childFragmentManager.popBackStack()
        }
    }



    //Conecta la vista de la bottom bar
    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }

}