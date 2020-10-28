package gads.mobile.ecom05.fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import gads.mobile.ecom05.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var ref : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("User")

        haveAnAccountText.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_signupFragment2_to_loginFragment2)
        }

        signupLoginBtn.setOnClickListener {

            signUp(nameEditText.text.toString(), emailEditText.text.toString(),
                passwordEditText.text.toString())
        }

    }

    private fun signUp(fullName : String, email : String, password : String){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->

                if (task.isSuccessful){
                    val user = HashMap<String, String>()
                    user["FullName"] = fullName
                    user["Email"] = email
                    ref.setValue(user)

                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_signupFragment2_to_mainActivity22)
                    Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(context, "Sign up failed, Try again...", Toast.LENGTH_SHORT)
                        .show()
                }

            }

    }

}