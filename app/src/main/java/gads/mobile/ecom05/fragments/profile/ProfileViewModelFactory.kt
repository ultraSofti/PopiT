package gads.mobile.ecom05.fragments.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gads.mobile.ecom05.fragments.detail.DetailViewModel
import gads.mobile.ecom05.repositories.UserCredentialRepository
import gads.mobile.ecom05.repositories.WineRepository

class ProfileViewModelFactory(private val application: Application, private val userCredentialRepository: UserCredentialRepository) : ViewModelProvider.AndroidViewModelFactory(
    application
) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(userCredentialRepository) as T
        else
            throw IllegalAccessException("unexpected view-model class")
    }
}