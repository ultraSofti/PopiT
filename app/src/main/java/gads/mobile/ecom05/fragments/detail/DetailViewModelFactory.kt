package gads.mobile.ecom05.fragments.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gads.mobile.ecom05.database.WineDao
import gads.mobile.ecom05.repositories.WineRepository

class DetailViewModelFactory(private val application: Application,private val wineRepository: WineRepository) : ViewModelProvider.AndroidViewModelFactory(
    application
) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
        return DetailViewModel(wineRepository) as T
        else
            throw IllegalAccessException("unexpected view-model class")
    }
}