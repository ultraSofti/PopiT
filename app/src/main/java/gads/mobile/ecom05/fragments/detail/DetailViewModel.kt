package gads.mobile.ecom05.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gads.mobile.ecom05.database.WineDao
import gads.mobile.ecom05.models.Wine
import gads.mobile.ecom05.repositories.WineRepository
import kotlinx.coroutines.*

class DetailViewModel  (private val wineRepository: WineRepository) : ViewModel() {
    private val uiScope= CoroutineScope(Dispatchers.Main + Job())



     private var _wines=MutableLiveData<List<Wine>>()
         val wine: MutableLiveData<List<Wine>>
             get() =_wines


    init{
           uiScope.launch {
               withContext(Dispatchers.IO){

                   _wines.value=wineRepository.getAllWines()
               }
           }
       }
    fun addSelectedWine(selectedWine: Wine) {
        uiScope.launch {
            withContext(Dispatchers.IO){
                wineRepository.insertWine(selectedWine)
            }
        }
    }
    override fun onCleared() {

        uiScope.cancel()
    }
}