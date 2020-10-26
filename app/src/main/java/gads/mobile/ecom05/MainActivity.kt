package gads.mobile.ecom05


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.home_nav_host)
        setSupportActionBar(mainToolBar)
        val bottomView: BottomNavigationView = findViewById(R.id.bottomNavView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_search,
            R.id.navigation_cart, R.id.navigation_detail, R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (Navigation.findNavController(this, R.id.home_nav_host)
                .currentDestination?.id == R.layout.fragment_home){
            super.onBackPressed()
            finishAffinity()

        }else super.onBackPressed()
    }



}