package gads.mobile.ecom05.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Entity(tableName = "Wine")
@Parcelize
data class Wine(
    @PrimaryKey(autoGenerate = true)
    var wineId: Int = 0,
    @ColumnInfo(name = "alc") var alc: String = " ",
    @ColumnInfo(name = "name") var name: String = " ",
    @ColumnInfo(name = "newest") var newest: String = " ",
    @ColumnInfo(name = "price") var price: String = " ",
    @ColumnInfo(name = "product_desc") var product_desc: String = " ",
    @ColumnInfo(name = "product_img") var product_img: String = " ",
    @ColumnInfo(name = "product_origin") var product_origin: String = " ",
    @ColumnInfo(name = "product_size") var product_size: String = " ",
    @ColumnInfo(name = "quantity") var quantity: String = " ",
    var isAddedToCart: Boolean = false

) : Parcelable {

}