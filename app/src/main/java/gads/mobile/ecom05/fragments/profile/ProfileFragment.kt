package gads.mobile.ecom05.fragments.profile

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import gads.mobile.ecom05.R
import gads.mobile.ecom05.repositories.UserCredentialRepository
import kotlinx.android.synthetic.main.dialog_change_password.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firebaseAuth=FirebaseAuth.getInstance()
        val application= requireActivity().application
        val userCredentialRepository=UserCredentialRepository(firebaseAuth,application.applicationContext)
        val profileViewModelFactory=ProfileViewModelFactory(application,userCredentialRepository)
        viewModel = ViewModelProvider(this,profileViewModelFactory)[ProfileViewModel::class.java]

     setHasOptionsMenu(true)

        val user=viewModel.getCurrentUser()
       user?.let {
           updateUi(it)
       }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            if(button_done.isVisible){
                hideDoneButton()
            }

            if (this.isEnabled){
                this.isEnabled=false
                requireActivity().onBackPressed()
            }
        }

            button_done.setOnClickListener {
                editUserProfileEvent()
            }

    }

    private fun editUserProfileEvent() {
        val email = user_email.text.toString()
        val name = user_name.text.toString()
        var containsEmptyField = false
        if (email.isEmpty()) {
            user_email.error = "required"
            containsEmptyField = true
        }
        if (name.isEmpty()) {
            user_name.error = "required"
            containsEmptyField = true
        }
        if (!containsEmptyField) {
            // updates
            viewModel.updateEmail(email)
            // ToDo update userName using sharedPreference
            hideDoneButton()
        }
    }

    private fun hideDoneButton() {
        button_done.visibility = View.GONE
    }

    private fun updateUi(user: FirebaseUser) {
       //TODO update user name as well. we can get it from sharedPreference

        user_email.setText(user.email,TextView.BufferType.EDITABLE)


    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.profile_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.edit_profile->{
                user_name.isFocusable=true
                user_email.isFocusable=true

                button_done.visibility=View.VISIBLE
                true
            }
            R.id.sign_out->{
                viewModel.signCurrentUserOut()
                true
            }
            R.id.change_password -> {
                changePassWordEvent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun changePassWordEvent() {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(
                R.layout.dialog_change_password,
                null
            )
            builder.setView(view)
                .setTitle("Change Password")
                .setPositiveButton("OK") { dialog, id ->
                    val newPassWord = view.new_password.text.toString()
                    val confirmPassWord = view.confirm_password.text.toString()
                    if (newPassWord.isNotEmpty() and (confirmPassWord.isNotEmpty())) {
                        if (newPassWord == confirmPassWord) {
                            viewModel.updatePassword(newPassWord)
                        } else
                            Toast.makeText(view.context, "does not match", Toast.LENGTH_SHORT)
                                .show()
                    }
                }.create()
        }
    }
}