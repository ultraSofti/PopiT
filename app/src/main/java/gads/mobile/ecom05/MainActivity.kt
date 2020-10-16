package gads.mobile.ecom05

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import gads.mobile.ecom05.fragments.walkthrough.ONBOARDING_PREF
import gads.mobile.ecom05.fragments.walkthrough.OnBoardingScreenFragment
import gads.mobile.ecom05.fragments.welcomeAuth.WelcomeAuthPromptFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportActionBar?.hide()

        if (onBoardingFinished()) {
            val welcomeAuthPromptFragment = WelcomeAuthPromptFragment()
            launchScreenFragment(welcomeAuthPromptFragment)

        } else {
            val onBoardingScreenFragment = OnBoardingScreenFragment()
            launchScreenFragment(onBoardingScreenFragment)

        }
    }

    private fun launchScreenFragment(fragment: Fragment) {
        val containerId = container.id
        supportFragmentManager.beginTransaction().replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {


        finish()
    }

    private fun onBoardingFinished(): Boolean {
        val pref = getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        return pref.getBoolean("firstTime", false)
    }

}