package gads.mobile.ecom05

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import gads.mobile.ecom05.adapters.WalkThroughScreenPagerAdapter
import gads.mobile.ecom05.fragments.ONBOARDING_PREF
import gads.mobile.ecom05.fragments.OnBoardingScreenFragment
import gads.mobile.ecom05.fragments.WelcomeAuthPromptFragment
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