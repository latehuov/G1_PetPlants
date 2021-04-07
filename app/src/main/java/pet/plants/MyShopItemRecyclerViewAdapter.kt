package pet.plants

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import pet.plants.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyShopItemRecyclerViewAdapter(
        private val values: List<DummyItem>)
    : RecyclerView.Adapter<MyShopItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_shopitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.productImange.setImageResource(R.drawable.yetanother_drawable)
        holder.productName.text = item.id
        holder.productPrice.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImange : ImageView = view.findViewById((R.id.ShopProductImage))
        val productName: TextView = view.findViewById(R.id.itemName)
        val productPrice: TextView = view.findViewById(R.id.itemPrice)

        override fun toString(): String {
            return super.toString() + " '" + productPrice.text + "'"
        }
    }
}