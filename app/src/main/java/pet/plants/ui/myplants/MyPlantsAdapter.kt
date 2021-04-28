package pet.plants.ui.myplants

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R
import java.io.IOException

class MyPlantsAdapter(val plantList: ArrayList<PlantData>) :
        RecyclerView.Adapter<MyPlantsAdapter.PlantViewHolder>() {

    lateinit var navController: NavController

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantName: TextView = itemView.findViewById(R.id.plantName)
        val plantButton: Button = itemView.findViewById(R.id.plantInfoPage)



    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_plant_item, parent, false)
        navController = parent.findNavController()
        return PlantViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return plantList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val item = plantList[position]
        holder.plantButton.setOnClickListener{
            val action = MyPlantsFragmentDirections.actionNavigationMyplantsToNavigationPlantpage(item)
            navController.navigate(action)
        }
        holder.plantName.text = item.name
    }

}