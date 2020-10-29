package gads.mobile.ecom05.repositories

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class UserCredentialRepository(private val firebaseAuth: FirebaseAuth,val context: Context) {
private var currentUser=this.getCurrentUser()
    fun getCurrentUser()=firebaseAuth.currentUser
    fun updateProfilePassWord(newPassWord:String){

        currentUser!!.updatePassword(newPassWord).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful)
                Toast.makeText(context,"Updated",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        })



    }
    fun updateProfileEmail(newEmail:String){

        currentUser!!.updateEmail(newEmail).addOnCompleteListener(OnCompleteListener {
               if (it.isSuccessful)
                   Toast.makeText(context,"Updated",Toast.LENGTH_LONG).show()
                 else
                   Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        })
    }

    fun signOutUser(){
        firebaseAuth.signOut()
    }
}