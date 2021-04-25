package pet.plants.ui.myplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R
import pet.plants.ui.myplants.PlantData

class MyPlantsAdapter(val plants : ArrayList<PlantData>) : RecyclerView.Adapter<MyPlantsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val plantname : TextView = itemView.findViewById(R.id.thePlant)
        val img : ImageView = itemView.findViewById(R.id.myPlantsPlaceHolder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_plant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = plants[position]
        holder.img.setImageResource(R.drawable.trauma2)
        holder.plantname.text = item.name

    }

    override fun getItemCount() = plants.size

}