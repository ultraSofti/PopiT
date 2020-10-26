package gads.mobile.ecom05

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.findNavController

import gads.mobile.ecom05.fragments.walkthrough.ONBOARDING_PREF

class WelcomeAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_auth)


        val navController = findNavController(R.id.auth_nav_host)
        if (onBoardingFinished()) {
           navController
               .navigate(R.id.action_onBoardingScreenFragment_to_welcomeAuthPromptFragment)

        } else {
            navController.navigate(R.id.onBoardingScreenFragment)
        }

    }


    private fun onBoardingFinished(): Boolean {
        val pref = getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        return pref.getBoolean("firstTime", false)
    }


}