package pet.plants.ui.myplants.data

import pet.plants.R
import pet.plants.ui.myplants.MyPlantsFragment

class PlantData(val context: MyPlantsFragment) {
    fun getPlantList(): Array<String> {
        // Return plant name list from string resources
        return context.resources.getStringArray(R.array.plant_names)
    }
    fun getPlantSpecies(): Array<String> {
        // Return plant species list from string resources
        return context.resources.getStringArray(R.array.plant_species)
    }
    fun getPlantDescription(): Array<String> {
        // Return plant species list from string resources
        return context.resources.getStringArray(R.array.plant_descriptions)
    }
}