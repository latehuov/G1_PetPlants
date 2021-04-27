package pet.plants.ui.myplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R

class MyPlantsAdapter(val plantList: Array<String>) :
        RecyclerView.Adapter<MyPlantsAdapter.PlantViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantName: TextView = itemView.findViewById(R.id.plantName)

        fun bind(word: String) {
            plantName.text = word
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_plant_item, parent, false)

        return PlantViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return plantList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plantList[position])
    }
}