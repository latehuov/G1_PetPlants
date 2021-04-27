package pet.plants.ui.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R


class MyPlantsFragment : Fragment() {
    private lateinit var RcView : RecyclerView
    private lateinit var PlantButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_my_plants, container, false)
        val item = inflater.inflate(R.layout.my_plant_item, container,false)
        val plantList = PlantData(this).getPlantList()

        RcView = view.findViewById(R.id.plantRecycler)
        RcView.layoutManager = LinearLayoutManager(context)
        RcView.adapter = MyPlantsAdapter(plantList)
        PlantButton = item.findViewById(R.id.plantInfoPage)

        PlantButton.setOnClickListener{
            findNavController().navigate(R.id.navigation_plantpage)
        }

        return view
    }
}