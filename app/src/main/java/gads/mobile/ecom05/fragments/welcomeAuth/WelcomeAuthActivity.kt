package gads.mobile.ecom05.fragments.welcomeAuth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import gads.mobile.ecom05.R
import gads.mobile.ecom05.fragments.ONBOARDING_PREF
import gads.mobile.ecom05.fragments.OnBoardingScreenFragment
import gads.mobile.ecom05.fragments.WelcomeAuthPromptFragment

class WelcomeAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_auth)
        this.supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment)
        if (onBoardingFinished()) {
           navController
               .navigate(R.id.action_onBoardingScreenFragment_to_welcomeAuthPromptFragment)
//            This block is replaced with navigation graph
//            val welcomeAuthPromptFragment = WelcomeAuthPromptFragment()
//            launchScreenFragment(welcomeAuthPromptFragment)

        } else {
            navController.navigate(R.id.onBoardingScreenFragment)
        }
    }
    private fun onBoardingFinished(): Boolean {
        val pref = getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        return pref.getBoolean("firstTime", false)
    }
    override fun onBackPressed() {
        finish()
    }

}