package pet.plants.ui.shop

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopItemData (val id:String, val name : String, val img: String?, val price :String?, val description :String? = "", val species : String? = "", val tags : ArrayList<String>) : Parcelable{
    companion object{
        fun from(map: MutableMap<String, Any>)= object  {
            val id by map
            val name by map
            val img by map
            val price by map
            val description by map
            val species by map
            val tags by map
            val data = ShopItemData(id.toString(), name.toString(), img.toString(), price.toString(), description.toString(), species.toString(), tags as ArrayList<String>)
        }.data
    }
}