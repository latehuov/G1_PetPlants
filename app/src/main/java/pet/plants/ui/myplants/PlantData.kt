package pet.plants.ui.myplants

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pet.plants.R
import pet.plants.ui.myplants.MyPlantsFragment
import pet.plants.ui.shop.ShopItemData

@Parcelize
data class PlantData(val id: String, val name: String, val species: String, val description: String?): Parcelable{
    companion object{
        fun from(map: MutableMap<String, String>)= object  {
            val id by map
            val name by map
            val description by map
            val species by map
            val data = PlantData(id, name, description, species)
        }.data
    }
}


