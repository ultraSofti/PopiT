package gads.mobile.ecom05.fragments.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gads.mobile.ecom05.fragments.profile.ProfileViewModel
import gads.mobile.ecom05.repositories.RealTimeDatabaseRepository

class SearchViewModelFactory(private val application: Application, private val realTimeDatabaseRepository: RealTimeDatabaseRepository) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java))
            return SearchViewModel(realTimeDatabaseRepository) as T
        else
            throw IllegalAccessException("unexpected view-model class")
    }
}