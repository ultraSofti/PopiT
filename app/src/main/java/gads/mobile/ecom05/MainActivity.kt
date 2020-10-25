package gads.mobile.ecom05

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import gads.mobile.ecom05.adapters.WalkThroughScreenPagerAdapter


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome_auth.*

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

    //   This block has been replaced with navigation
//    private fun launchScreenFragment(fragment: Fragment) {
//        val containerId = container.id
//        supportFragmentManager.beginTransaction().replace(containerId, fragment)
//            .addToBackStack(null)
//            .commit()
//    }

    //  This code Block has been moved to Welcome Auth Activity
//    override fun onBackPressed() {
//        finish()
//    }
//
//    private fun onBoardingFinished(): Boolean {
//        val pref = getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
//        return pref.getBoolean("firstTime", false)
//    }

}