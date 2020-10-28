package gads.mobile.ecom05.database

import android.content.Context
import android.os.Build
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import gads.mobile.ecom05.models.Wine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP))
class LocalDataBaseRoboLectricTest{

    private lateinit var wineDao: WineDao
    lateinit var localDataBase: LocalDataBase

    @Before
    fun setUp(){
        val context= ApplicationProvider.getApplicationContext<Context>()
      //  localDataBase= Room.inMemoryDatabaseBuilder(context,LocalDataBase::class.java).allowMainThreadQueries().build()
        localDataBase= getInstance(context)
        wineDao=localDataBase.wineDao
    }
    @Test
    fun write_and_read()= runBlocking{
        val wine= Wine(name = "best choice")
        withContext(Dispatchers.IO){
            wineDao.insertWine(wine)
            assertEquals(wine.name, wineDao.getFirstWine().name)
        }
    }

    @Test
    fun should_update_isAddToCart_value()= runBlocking{
        val wine= Wine(name = "best choice")
        withContext(Dispatchers.IO){
            wineDao.insertWine(wine)
            val wineInserted=wineDao.getFirstWine()
            val wineInsertedStatus=wineInserted.isAddedToCart
              // updating
            wineInserted.isAddedToCart=true
            wineDao.updateWine(wineInserted)
           assertTrue(wineDao.getFirstWine().isAddedToCart)
            assertFalse(wineInsertedStatus)
        }
    }
    @Test

    fun should_autogerate_id()= runBlocking{
        withContext(Dispatchers.IO){
            val wine= Wine(name = "best choice")
            wineDao.insertWine(wine)
            val wineOne= Wine(name = "awesome")
            wineDao.insertWine(wineOne)
            val idOne= wineDao.getAllWines()[1].wineId
            val id= wineDao.getAllWines()[0].wineId
            assertEquals(3,id + idOne)
        }

    }
    @Test

    fun should_not_return_emptylist()= runBlocking{
        withContext(Dispatchers.IO){
            val wine= Wine(name = "best choice")
            wineDao.insertWine(wine)
            val wineOne= Wine(name = "awesome")
            wineDao.insertWine(wineOne)
            val wineSize=wineDao.getAllWines().size
            assertEquals(2,wineSize)
        }

    }
    @After
    fun close(){
        localDataBase.close()
    }
}