package gads.mobile.ecom05.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gads.mobile.ecom05.models.Wine

@Database(entities = [Wine::class],version = 1, exportSchema = false)
abstract class LocalDataBase:RoomDatabase() {
    abstract val wineDao:WineDao




}
private  lateinit var instance: LocalDataBase
fun getInstance(context: Context):LocalDataBase{
    if(!::instance.isInitialized){
        synchronized(LocalDataBase::class.java){
            instance= Room.databaseBuilder(context,LocalDataBase::class.java,"mydatabase.db")
                .build()
        }
    }
    return instance
}