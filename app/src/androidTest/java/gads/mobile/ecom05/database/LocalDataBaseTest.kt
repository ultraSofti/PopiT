package gads.mobile.ecom05.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import gads.mobile.ecom05.models.Wine
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDataBaseTest{
    lateinit var localDataBase: LocalDataBase
    lateinit var wineDao: WineDao
    @Before
    fun setUP(){
        val context= ApplicationProvider.getApplicationContext<Context>()
        localDataBase= Room.inMemoryDatabaseBuilder(context,LocalDataBase::class.java).build()
        wineDao=localDataBase.wineDao
    }
    @Test
    @Throws(Exception::class)
    fun should_write_and_read(){
        val wineOne= Wine(name="my name")
        wineDao.insertWine(wineOne)
        val wineFromDataBase= wineDao.getAllWines()[0]
        assertSame(wineOne,wineFromDataBase)

    }
    @After
    @Throws(IOException::class)
    fun closeDataBase(){
        localDataBase.close()
    }
}