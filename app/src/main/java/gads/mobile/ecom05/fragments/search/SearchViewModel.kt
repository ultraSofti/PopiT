package gads.mobile.ecom05.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gads.mobile.ecom05.models.Wine
import gads.mobile.ecom05.repositories.RealTimeDatabaseRepository

class SearchViewModel(private val realTimeDatabaseRepository: RealTimeDatabaseRepository) :
    ViewModel() {
    private val _filterStatus = MutableLiveData<Filter>()
    val filterStatus: LiveData<Filter>
        get() = _filterStatus
     init {

         _filterStatus.value =Filter.SEARCH_ALL
     }
    fun setFilterStatus(filter: Filter) {
        _filterStatus.value = filter
    }

    fun getAllWines(): List<Wine> {
      return  realTimeDatabaseRepository.getAllWines()
    }

    fun getNonAlcWines(): List<Wine> {
        return realTimeDatabaseRepository.getNonAlcWine()
    }

    fun getCheapDeals(): List<Wine> {
        return realTimeDatabaseRepository.getCheapDeals()
    }


}