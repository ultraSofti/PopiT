package gads.mobile.ecom05

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import gads.mobile.ecom05.fragments.walkthrough.ONBOARDING_PREF

class WelcomeAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_auth)
//        this.supportActionBar?.hide()
//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//        this.supportActionBar?.apply {
//            title =""
//            setDisplayShowTitleEnabled(false)
//            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }

        val navController = findNavController(R.id.auth_nav_host)
        if (onBoardingFinished()) {
           navController
               .navigate(R.id.action_onBoardingScreenFragment_to_welcomeAuthPromptFragment)
//            This block is replaced with navigation graph
//            val welcomeAuthPromptFragment = WelcomeAuthPromptFragment()
//            launchScreenFragment(welcomeAuthPromptFragment)
        } else {
            navController.navigate(R.id.onBoardingScreenFragment)
        }
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.auth_nav_host)
//        return navController.navigateUp()
//    }

    private fun onBoardingFinished(): Boolean {
        val pref = getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        return pref.getBoolean("firstTime", false)
    }
//    override fun onBackPressed() {
//        finish()
//    }

}