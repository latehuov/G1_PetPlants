package pet.plants.ui.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R


class MyPlantsFragment : Fragment() {
    private lateinit var RcView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_my_plants, container, false)
        val plantList = PlantData(this).getPlantList()

        RcView = view.findViewById(R.id.plantRecycler)
        RcView.layoutManager = LinearLayoutManager(context)
        RcView.adapter = MyPlantsAdapter(plantList)

        return view
    }
}