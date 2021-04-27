package pet.plants.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R
import pet.plants.ui.shop.ShopAdapter
import pet.plants.ui.shop.ShopItemData
import pet.plants.ui.shop.ShoppingData
import pet.plants.ui.shop.shopItemFragmentDirections

class cartAdapter(val shopitems : ArrayList<ShoppingData>?) : RecyclerView.Adapter<cartAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val prodname : TextView = itemView.findViewById(R.id.prodName)
        val price : TextView = itemView.findViewById(R.id.itemPrice)
        val img : ImageView = itemView.findViewById(R.id.ShopProductImage)
        val amount : TextView = itemView.findViewById(R.id.amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(shopitems == null)
        {
            holder.img.setImageResource(R.drawable.yetanother_drawable)
            holder.prodname.text = "nothing in cart"
            holder.price.text = "prices vary"
            holder.amount.text= "find your pet plants today"

        }else{
            val item = shopitems[position]
            holder.img.setImageResource(R.drawable.yetanother_drawable)
            holder.prodname.text = item.thing.name
            holder.price.text = item.thing.price
            holder.amount.text= item.amount.toString()
        }


    }

    override fun getItemCount() :Int{
        if(shopitems == null)
            return 1
        else return shopitems.size
    }
}