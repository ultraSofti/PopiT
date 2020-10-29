package gads.mobile.ecom05.repositories

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.*
import gads.mobile.ecom05.models.Wine

class RealTimeDatabaseRepository( private val reference: DatabaseReference, val context: Context) {

    fun getAllWines(): ArrayList<Wine> {
        val wines= arrayListOf<Wine>()
        val listener = object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               snapshot.children.forEach(){
                 wines.add(it.value as Wine)
               }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        val query=reference.child("wine").orderByKey().addListenerForSingleValueEvent(listener)
        return wines

    }

    fun getNonAlcWine(): List<Wine> {
        return getAllWines().filter {
            it.alc=="0"|| it.alc==""
        }
    }
    fun getCheapDeals()=getAllWines().filter {
        it.price.toDouble()>=10.00
    }
}