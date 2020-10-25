package gads.mobile.ecom05.fragments.welcomeAuth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_welcome_auth_prompt.*

class WelcomeAuthPromptFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_auth_prompt,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_skip_login_screen.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_welcomeAuthPromptFragment_to_mainActivity2)


        }
        button_login.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_welcomeAuthPromptFragment_to_loginFragment2)
        }
        button_sign_up.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_welcomeAuthPromptFragment_to_signupFragment2)
        }

    }
}