package gads.mobile.ecom05.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
//
//        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            login(emailEditText.text.toString(), passwordEditText.text.toString())
        }

    }

    private fun login(email : String, password : String){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_loginFragment2_to_mainActivity22)
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    }

                }
    }

}