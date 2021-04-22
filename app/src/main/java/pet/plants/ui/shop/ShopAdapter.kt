package pet.plants.ui.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R

class ShopAdapter(val shopitems : ArrayList<ShopItemData>) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val prodname : TextView = itemView.findViewById(R.id.prodName)
        val price : TextView = itemView.findViewById(R.id.itemPrice)
        val description : TextView = itemView.findViewById(R.id.description)
        val img : ImageView = itemView.findViewById(R.id.ShopProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shopitems[position]
        holder.img.setImageResource(R.drawable.yetanother_drawable)
        holder.prodname.text = item.name
        holder.price.text = item.price
        holder.description.text= item.description

    }

    override fun getItemCount() = shopitems.size

}