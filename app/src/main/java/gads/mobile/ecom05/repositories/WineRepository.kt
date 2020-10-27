package gads.mobile.ecom05.repositories

import gads.mobile.ecom05.database.WineDao
import gads.mobile.ecom05.models.Wine

class WineRepository(private val wineDao:WineDao) {

    fun insertWine(wine: Wine){
        wineDao.insertWine(wine)
    }
    fun getAllWines()=wineDao.getAllWines()
    fun clearWines(){
        wineDao.clearWines()
    }
}