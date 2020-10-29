package gads.mobile.ecom05.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gads.mobile.ecom05.R
import gads.mobile.ecom05.models.Wine
import kotlinx.android.synthetic.main.search_list_item.view.*

class SearchViewAdapter(private val wines: List<Wine>, private val listener: (Wine) -> Unit) :
    RecyclerView.Adapter<SearchViewAdapter.ItemWineHolder>() {

    class ItemWineHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currentWine: Wine, listener: (Wine) -> Unit) {
            itemView.item_alc.text = currentWine.alc
            itemView.item_description.text = currentWine.product_desc
            itemView.item_price.text = currentWine.price
            Glide.with(itemView).load(currentWine.product_img)
                .into(itemView.item_image)

            itemView.setOnClickListener {
                listener.invoke(currentWine)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWineHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_item, parent, false)
        return ItemWineHolder(view)
    }

    override fun onBindViewHolder(holder: ItemWineHolder, position: Int) {
        val currentWine = wines[position]
        holder.bind(currentWine, listener)
    }

    override fun getItemCount(): Int {
        return wines.size
    }
}