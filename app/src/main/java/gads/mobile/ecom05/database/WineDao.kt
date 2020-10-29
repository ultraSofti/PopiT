package gads.mobile.ecom05.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import gads.mobile.ecom05.models.Wine

@Dao
interface WineDao {
    @Insert
    fun insertWine(wine: Wine)

    @Query("DELETE FROM Wine")
    fun clearWines()

    @Query("SELECT * FROM Wine ")
    fun getAllWines(): List<Wine>

    @Query("SELECT * FROM Wine LIMIT 1")
    fun getFirstWine(): Wine

    @Update
    fun updateWine(wine: Wine)

}