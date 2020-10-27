package gads.mobile.ecom05.models

import org.junit.Assert.*
import org.junit.Test

class WineTest{
    @Test
    fun createWine(){
        val wine=Wine(name = "all star")
    }

    @Test
    fun should_set_isaddtocart_valaue(){
        val wine2=Wine(name = "devin")
        val wine1=Wine(name = "all star")
           wine2.isAddedToCart=true
        assertTrue(wine2.isAddedToCart)
        assertFalse(wine1.isAddedToCart)
    }
}