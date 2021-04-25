package pet.plants.ui.myplants

data class PlantData (val id:String, val name : String, val img: String?, val description :String? = "", val species : String? = "") {
    companion object {
        fun from(map: MutableMap<String, String>) = object {
            val id by map
            val name by map
            val img by map
            val description by map
            val species by map

            val data = PlantData(id, name, img, description, species)
        }.data
    }
}