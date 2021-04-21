package pet.plants

data class ShopItemData (val id:String, val name : String, val img: String?, val price :String?, val description :String? = "", val species : String? = ""){
    companion object{
        fun from(map: MutableMap<String, String>)= object  {
            val id by map
            val name by map
            val img by map
            val price by map
            val description by map
            val species by map

            val data = ShopItemData(id, name, img, price, description, species)
        }.data
    }
}