package gads.mobile.ecom05.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import gads.mobile.ecom05.R
import gads.mobile.ecom05.adapters.WalkThroughScreenPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_onboarding_screen.view.*
import java.util.prefs.Preferences
const val ONBOARDING_PREF="gads.mobile.ecom05.onboarding"
class OnBoardingScreenFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding_screen,container,false)

      view.viewpager.adapter= WalkThroughScreenPagerAdapter(requireActivity())
        TabLayoutMediator(view.tablayout,view.viewpager,true) { _, _ ->

        }.attach()
        view.button_get_started.setOnClickListener {
            onBoardingFinished()
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_onBoardingScreenFragment_to_welcomeAuthPromptFragment)
        }
        return view

    }
//This code block is replaced with navigation graph
//    private fun addWelcomeScreenToContainer() {
//      val welcomeAuthPromptFragment=WelcomeAuthPromptFragment()
//        val fragmentTransaction= fragmentManager?.beginTransaction()
//        val container= requireActivity().container.id
//
//            fragmentTransaction?.replace(container,welcomeAuthPromptFragment)
//        fragmentTransaction?.addToBackStack(null)
//        fragmentTransaction?.commit()
//
//    }

    private fun onBoardingFinished() {
        val sharePreferences=requireActivity().getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        val editor=sharePreferences.edit()
        editor.putBoolean("firstTime",true)
        editor.apply()
    }
}