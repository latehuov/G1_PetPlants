package pet.plants.ui.shop

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pet.plants.MainActivity
import pet.plants.R


class ShopAdapter(val shopitems : ArrayList<ShopItemData>) : RecyclerView.Adapter<ShopAdapter.ViewHolder>(), Filterable {


    var filteredList = ArrayList<ShopItemData>()

    init {
        filteredList = shopitems
    }

    lateinit var navController: NavController
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val mainDiv : CardView = itemView.findViewById(R.id.mainDiv)
        val prodname : TextView = itemView.findViewById(R.id.prodName)
        val price : TextView = itemView.findViewById(R.id.itemPrice)
        val description : TextView = itemView.findViewById(R.id.description)
        val img : ImageView = itemView.findViewById(R.id.ShopProductImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        navController = parent.findNavController()
        return ViewHolder(view)
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.mainDiv.setOnClickListener{ view ->
            val action = shopItemFragmentDirections.actionNavigationShopToNavigationDetails(item)
            navController.navigate(action)
        }
        Picasso.get().load(item.img).into(holder.img)
        holder.prodname.text = item.name
        holder.price.text = item.price
        holder.description.text= item.description

    }

    override fun getItemCount() = filteredList.size


    override fun getFilter(): Filter {
    return object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
             var searchString = constraint
             if(searchString == null)
                 filteredList = shopitems
             else
             {
                 val results = ArrayList<ShopItemData>()
                 for (row in shopitems)
                 {
                     for (tag in row.tags)
                     {
                         if (tag.contains(searchString, true))
                         {
                             results.add(row)
                             break
                         }
                     }
                 }
                 filteredList = results
             }
            var filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredList = results?.values as ArrayList<ShopItemData>
            notifyDataSetChanged()
        }

    }
    }

}