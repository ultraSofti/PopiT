package gads.mobile.ecom05.fragments.profile

import androidx.lifecycle.ViewModel
import gads.mobile.ecom05.repositories.UserCredentialRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class ProfileViewModel(private val userCredentialRepository: UserCredentialRepository) :
    ViewModel() {


    fun getCurrentUser() = userCredentialRepository.getCurrentUser()
    fun signCurrentUserOut() {
        userCredentialRepository.signOutUser()
    }

    fun updateEmail(email: String) {
        userCredentialRepository.updateProfileEmail(email)
    }

    fun updatePassword(passWord: String) {
        userCredentialRepository.updateProfilePassWord(passWord)
    }
}